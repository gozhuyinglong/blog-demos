package io.github.gozhuyinglong.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author 码农StayUp
 * @date 2021/11/23 0023
 */
public class NettyNioServerHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收到的消息
        ByteBuf in = (ByteBuf) msg;
        try {
            byte[] b = new byte[in.readableBytes()];
            in.readBytes(b);
            System.out.println("------------" + (++count));
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
