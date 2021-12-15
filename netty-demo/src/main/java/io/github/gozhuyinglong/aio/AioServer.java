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

    private AsynchronousServerSocketChannel serverSocketChannel;

    public AioServer(int port) {
        try {
            // 打开一个异步的 ServerSocket 通道
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            // 绑定本地端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            System.out.printf("[%s] - 服务端启动了，端口为：%s\n", Thread.currentThread().getName(), port);
            // 调用接收处理器
            acceptHandler();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收处理器
     */
    private void acceptHandler() {
        // 接收一个客户端连接，若有客户端连接会自动通知 CompletionHandler 进行处理。注：该操作不会阻塞
        serverSocketChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AioServer>() {
            @Override
            public void completed(AsynchronousSocketChannel result, AioServer attachment) {
                try {
                    // 一个客户端连接后，继续接收下一个连接
                    attachment.acceptHandler();
                    System.out.printf("[%s] - 有一个客户端连上来了 - %s\n", Thread.currentThread().getName(), result.getRemoteAddress());
                    // 申请一个1024个字节的缓冲区
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    // Socket处理器
                    result.read(byteBuffer, byteBuffer, new SocketHandler(result));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, AioServer attachment) {
                // 一个客户端连接后，无论成功或失败都要接收下一个连接
                attachment.acceptHandler();
                System.out.printf("[%s] - 客户连接失败 - %s\n", Thread.currentThread().getName(), exc.getMessage());
            }
        });
    }

    /**
     * Socket 处理类
     */
    static class SocketHandler implements CompletionHandler<Integer, ByteBuffer> {

        private final AsynchronousSocketChannel asynchronousSocketChannel;

        SocketHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
            this.asynchronousSocketChannel = asynchronousSocketChannel;
        }

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            // 申请一个1024个字节的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 继续读取下一报文
            asynchronousSocketChannel.read(byteBuffer, byteBuffer, new SocketHandler(asynchronousSocketChannel));

            // 将缓冲区进行反转（刚才是写入，反转后变为读取）
            attachment.flip();
            // 读取缓冲区中的内容，并转为字符串
            String content = new String(attachment.array(), 0, result);
            System.out.printf("[%s] - 接收客户端发来的内容：%s\n", Thread.currentThread().getName(), content);
            // 清除缓冲区
            attachment.clear();

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
