package io.github.gozhuyinglong.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

/**
 * @author 码农StayUp
 * @date 2021/4/21 0021
 */
public class AioServer {

    private static final int PORT = 8080;

    public AioServer(int port) {
        try {
            // 打开一个异步的 ServerSocket 通道
            AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
            // 绑定本地端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            System.out.printf("[%s] - 服务端启动了，端口为：%s\n", Thread.currentThread().getName(), port);
            // 接收客户端连接
            serverSocketChannel.accept(serverSocketChannel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                @Override
                public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
                    try {
                        // 一个客户端连接后，继续接收下一个连接
                        attachment.accept(attachment, this);
                        System.out.printf("[%s] - 有一个客户端连上来了 - %s\n", Thread.currentThread().getName(), result.getRemoteAddress());
                        // 申请一个1024个字节的缓冲区
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        // 读取客户端数据
                        result.read(byteBuffer, byteBuffer, new ReadCompletionHandler(result));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
                    // 一个客户端连失败，继续接收下一个连接
                    attachment.accept(attachment, this);
                    System.out.printf("[%s] - 客户连接失败 - %s\n", Thread.currentThread().getName(), exc.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Socket 处理类
     */
    static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

        private final AsynchronousSocketChannel asynchronousSocketChannel;

        ReadCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
            this.asynchronousSocketChannel = asynchronousSocketChannel;
        }

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            // 客户端关闭通道，字节数为-1
            if(result == -1) {
                System.out.printf("[%s] - 客户端断开连接！\n", Thread.currentThread().getName());
                try {
                    // 关闭当前 Socket 通道
                    asynchronousSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            // 将缓冲区进行反转（刚才是写入，反转后变为读取）
            attachment.flip();
            // 读取缓冲区中的内容，并转为字符串
            String content = new String(attachment.array(), 0, result);
            System.out.printf("[%s] - 接收客户端发来的内容：%s\n", Thread.currentThread().getName(), content);
            // 清除缓冲区
            attachment.clear();
            // 继续读取下一报文
            asynchronousSocketChannel.read(attachment, attachment, new ReadCompletionHandler(asynchronousSocketChannel));
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            System.out.printf("[%s] - 客户端断开连接！\n", Thread.currentThread().getName());
            try {
                // 关闭当前 Socket 通道
                asynchronousSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {

        AioServer server = new AioServer(PORT);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

    }
}
