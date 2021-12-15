package io.github.gozhuyinglong.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author 码农StayUp
 * @date 2021/4/16 0016
 */
public class NioClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        // 打开一个 Socket 通道，并绑定指定的服务端地址与端口
        SocketChannel socketChannel = SocketChannel.open();
        // 将该通道设为非阻塞
        socketChannel.configureBlocking(false);
        // 连接远程地址
        socketChannel.connect(new InetSocketAddress(HOST, PORT));

        // 申请一个1024字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 获取控制台输入内容
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
            // 将缓冲区中的内容写入到 Socket 通道中
            socketChannel.write(byteBuffer);
            // 清除缓冲区
            byteBuffer.clear();
        }
        scanner.close();
        socketChannel.close();
    }
}
