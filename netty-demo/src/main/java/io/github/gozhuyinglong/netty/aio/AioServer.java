package io.github.gozhuyinglong.netty.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhuYinglong
 * @date 2021/4/21 0021
 */
public class AioServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {

        // 打开一个 AsynchronousServerSocketChannel
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        // 将此通道与本地端口进行绑定
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        System.out.printf("[%s] - 服务端启动了，端口为：%s\n", Thread.currentThread().getName(), PORT);

        while (true) {
            // 监听客户端连接（同步）
            Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
            // 获取客户端 Socket 通道
            AsynchronousSocketChannel socketChannel = future.get();
            System.out.printf("[%s] - 有一个客户端连上来了 - %s\n", Thread.currentThread().getName(), socketChannel.getRemoteAddress());
            new Thread(() -> socketHandler(socketChannel)).start();
        }


    }

    private static void socketHandler(AsynchronousSocketChannel socketChannel) {
        // 申请一个1024个字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            // 读取该通道的内容至缓冲区（将内容写入缓冲区）
            try {
                if (socketChannel.read(byteBuffer).get() > 0) {
                    // 将缓冲区进行反转（刚才是写入，反转后变为读取）
                    byteBuffer.flip();
                    // 读取缓冲区中的内容，并转为字符串
                    String content = new String(byteBuffer.array(), 0, byteBuffer.limit());
                    System.out.printf("[%s] - 接收客户端发来的内容：%s\n", Thread.currentThread().getName(), content);
                    // 清除缓冲区
                    byteBuffer.clear();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
