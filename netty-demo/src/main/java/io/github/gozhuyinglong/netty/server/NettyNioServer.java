package io.github.gozhuyinglong.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @author 码农StayUp
 * @date 2021/11/23 0023
 */
public class NettyNioServer {

    private final int port;

    public NettyNioServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // boss多线程事件循环组，用于接收连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker多线程事件循环组，用于处理连接后的 I/O 数据
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务端引导程序，用于设置服务端配置
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossGroup, workerGroup) // 注册这两个线程组
                    .channel(NioServerSocketChannel.class) // 设置接收新连接时使用的 I/O 模型，NioServerSocketChannel 类实现了NIO多路复用模型
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 设置 childHandler，用于处理具体的 Channel 的请求
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new FixedLengthFrameDecoder(48));
//                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$".getBytes())));
//                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 1, 2, 0, 3));
                            ch.pipeline().addLast(new NettyNioServerHandler()); // 添加 I/O 事件的处理器
                        }

                    })
                    .option(ChannelOption.SO_BACKLOG, 128) // option用于设置套接字选项，SO_BACKLOG 用于设置连接队列大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，并开始接收传入的连接
            ChannelFuture channelFuture = sb.bind(port).sync();
            System.out.println("服务端启动成功，等待连接...");
            // 阻塞住，直到套接字关闭。
            channelFuture.channel().closeFuture().sync();

        } finally {
            // 优雅关闭两个线程组
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyNioServer(8080).run();
    }

}
