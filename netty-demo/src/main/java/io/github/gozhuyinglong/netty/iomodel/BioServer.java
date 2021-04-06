package io.github.gozhuyinglong.netty.iomodel;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ZhuYinglong
 * @date 2021/4/6 0006
 */
public class BioServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        // 为服务端创建 ServerSocket，并指定端口
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.printf("[%s] - 服务端启动了，端口为：%s\n", Thread.currentThread().getName(), PORT);

        while (true) {
            // 监听，等待客户端连接。该方法会阻塞，直到建立连接。
            Socket socket = serverSocket.accept();
            System.out.printf("[%s] - 有一个客户端连上来了\n", Thread.currentThread().getName());

            new Thread(() -> {
                try {
                    // 创建缓冲区数组，用于临时存储客户端发来的数据
                    byte[] bytes = new byte[1024];
                    // 通过 socket 获取输入流
                    InputStream inputStream = socket.getInputStream();
                    while (true) {
                        // 从输入流中读取数据，并将它们存储到缓冲区数组中。该方法会阻塞，直到输入数据可用、检查到文件结束或抛出异常
                        int read = inputStream.read(bytes);
                        if (read != -1) {
                            String content = new String(bytes, 0, read);
                            System.out.printf("[%s] - 接收客户端发来的内容：%s\n", Thread.currentThread().getName(), content);
                        } else {
                            System.out.printf("[%s] - 连接关闭...\n", Thread.currentThread().getName());
                            break;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
