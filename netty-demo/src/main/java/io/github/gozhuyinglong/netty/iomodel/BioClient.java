package io.github.gozhuyinglong.netty.iomodel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ZhuYinglong
 * @date 2021/4/6 0006
 */
public class BioClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        // 创建 Socket，并指向服务端主机地址及端口
        Socket socket = new Socket(HOST, PORT);
        System.out.printf("连接成功，主机：%s，端口：%s\n", HOST, PORT);
        // 获取输出流
        OutputStream outputStream = socket.getOutputStream();
        // 获取控制输入内容
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请输入：");
            String content = scanner.nextLine();
            if ("quit".equals(content)) {
                break;
            }
            // 将控制台内容写入输出流
            outputStream.write(content.getBytes());
        }
        scanner.close();
        socket.close();
    }
}
