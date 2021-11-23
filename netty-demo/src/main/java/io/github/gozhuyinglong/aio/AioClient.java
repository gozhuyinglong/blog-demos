package io.github.gozhuyinglong.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;

/**
 * @author 码农StayUp
 * @date 2021/4/22 0022
 */
public class AioClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        // 打开一个异步的 Socket 通道
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        // 连接指定的服务端，并同步获取连接结果。get()操作会阻塞，直到连接成功。
        socketChannel.connect(new InetSocketAddress(HOST, PORT)).get();
        // 申请一个1024字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 获取控制台输入内容，输入 quit 会退出输入，并关闭通道
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请输入：");
            String content = scanner.nextLine();
            if ("quit".equals(content)) {
                break;
            }
            // 将控制台输入的内容写入缓冲区
            byteBuffer.put(content.getBytes());
            // 反转缓冲区（从写入变为读取）
            byteBuffer.flip();
            // 将缓冲区中的内容写入到 Socket 通道中。get()操作会阻塞，直到写入成功。
            socketChannel.write(byteBuffer).get();
            // 清除缓冲区
            byteBuffer.clear();
        }
        scanner.close();
        socketChannel.close();
    }
}
