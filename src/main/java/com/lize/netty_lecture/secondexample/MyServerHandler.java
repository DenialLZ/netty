package com.lize.netty_lecture.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author: lize
 * @Date: 2020/5/14 16:15
 * @Description:
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("远程客户端地址和端口："+ctx.channel().remoteAddress()+"远程消息"+msg);

        ctx.channel().writeAndFlush("from server"+ UUID.randomUUID());


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
//        super.exceptionCaught(ctx, cause);
    }
}
