package io.github.gozhuyinglong.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author 码农StayUp
 * @date 2021/11/23 0023
 */
public class NettyNioClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接成功...");

        // 正常
        for (int i = 0; i < 10; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("服务器你好，我是一条完整的消息！".getBytes(StandardCharsets.UTF_8));
            ctx.writeAndFlush(byteBuf);
            TimeUnit.SECONDS.sleep(1);
        }

        // 发生粘包/拆包
//        for (int i = 0; i < 100; i++) {
//            ByteBuf byteBuf = Unpooled.copiedBuffer("服务器你好，我是一条完整的消息！".getBytes(StandardCharsets.UTF_8));
//            ctx.writeAndFlush(byteBuf);
//        }

        // 1.测试 FixedLengthFrameDecoder
//        for (int i = 0; i < 10; i++) {
//            ByteBuf byteBuf = Unpooled.copiedBuffer("服务器你好，我是一条完整的消息！".getBytes(StandardCharsets.UTF_8));
//            ctx.writeAndFlush(byteBuf);
//        }

        // 2.测试 LineBasedFrameDecoder
//        for (int i = 0; i < 10; i++) {
//            ByteBuf byteBuf = Unpooled.copiedBuffer("服务器你好，我是一条完整的消息！".getBytes(StandardCharsets.UTF_8));
//            ctx.writeAndFlush(byteBuf);
//        }

        // 3.测试 DelimiterBasedFrameDecoder
//        for (int i = 0; i < 10; i++) {
//            ByteBuf byteBuf = Unpooled.copiedBuffer("服务器你好，我是一条完整的消息！$".getBytes(StandardCharsets.UTF_8));
//            ctx.writeAndFlush(byteBuf);
//        }

        // 4.测试 LengthFieldBasedFrameDecoder
//        for (int i = 0; i < 10; i++) {
//            byte[] header = ByteBufUtil.decodeHexDump("EE");
//            byte[] length = ByteBufUtil.decodeHexDump("0030");
//            byte[] data = "服务器你好，我是一条完整的消息！".getBytes(StandardCharsets.UTF_8);
//            ByteBuf byteBuf = Unpooled.copiedBuffer(header, length, data);
//            ctx.writeAndFlush(byteBuf);
//        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收到的消息
        ByteBuf in = (ByteBuf) msg;
        try {
            byte[] b = new byte[in.readableBytes()];
            in.readBytes(b);
            System.out.println(new String(b, StandardCharsets.UTF_8));
        } finally {
            // 显式的释放消息
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
