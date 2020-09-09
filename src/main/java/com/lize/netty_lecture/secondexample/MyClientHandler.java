package com.lize.netty_lecture.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author: lize
 * @Date: 2020/5/14 16:31
 * @Description:
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("远程客户端地址和端口："+ctx.channel().remoteAddress()+"Client 远程消息"+msg);

        ctx.writeAndFlush("from client 客户端："+ LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自客户端的问候!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
//        super.exceptionCaught(ctx, cause);
    }
}
