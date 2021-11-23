package io.github.gozhuyinglong.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author 码农StayUp
 * @date 2021/4/16 0016
 */
public class NioServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        // 在本地打开一个 ServerSocket 通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 将该通道配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 绑定本地端口
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        System.out.printf("[%s] - 服务端启动了，端口为：%s\n", Thread.currentThread().getName(), PORT);

        // 打开一个选择器（多路复用）
        Selector selector = Selector.open();
        // 将该通道的接入事件注册到选择器中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 通过选择器轮询获取该通道上的 I/O 事件。该操作会阻塞
        while (selector.select() > 0) {
            // 获取选择器中所有准备就绪的事件，并进行迭代
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            // 遍历所有准备好的事件
            while (it.hasNext()) {
                // 获取当前事件
                SelectionKey selectionKey = it.next();
                // 从迭代器中移除当前事件
                it.remove();
                // 根据不同的事件，做不同的操作
                if (selectionKey.isAcceptable()) {
                    // 接收事件，表示一个 Socket 连接上来了
                    // 获取当前接入的通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.printf("[%s] - 有一个客户端连上来了 - %s\n", Thread.currentThread().getName(), socketChannel.getRemoteAddress());
                    // 将接入的通道设为非阻塞
                    socketChannel.configureBlocking(false);
                    // 将该接入的通道的读事件注册到选择器中
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 读取事件，表示有客户端发来消息
                    // 获取产生事件的通道
                    socketHandler((SocketChannel) selectionKey.channel());
                }
            }
        }
    }

    /**
     * 处理 Socket 通道
     * @param socketChannel
     * @throws IOException
     */
    private static void socketHandler(SocketChannel socketChannel) throws IOException {
        // 申请一个1024个字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 读取该通道的内容至缓冲区（将内容写入缓冲区）
        while (socketChannel.read(byteBuffer) > 0) {
            // 将缓冲区进行反转（刚才是写入，反转后变为读取）
            byteBuffer.flip();
            // 读取缓冲区中的内容，并转为字符串
            String content = new String(byteBuffer.array(), 0, byteBuffer.limit());
            System.out.printf("[%s] - 接收客户端发来的内容：%s\n", Thread.currentThread().getName(), content);
            // 清除缓冲区
            byteBuffer.clear();
        }
    }
}
