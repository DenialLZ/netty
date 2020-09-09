package com.lize.netty_lecture.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author: lize
 * @Date: 2020/5/14 17:16
 * @Description:
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //存储channel对象到channelGroup组中
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            if(channel != ch){
                ch.writeAndFlush(channel.remoteAddress()+"发送的消息："+msg+"\n");
            }else {
                ch.writeAndFlush("【自己】"+msg+"\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Added 新通道添加");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 - "+channel.remoteAddress()+"加入\n");
        channelGroup.add(channel);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Removed 链接中断");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 - "+channel.remoteAddress()+"离开\n");
        //netty会自动调用remove无需手动调用
//        channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel  Active 链接活动状态");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 - "+channel.remoteAddress()+"上线了\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Inactive 不活动状态");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 - "+channel.remoteAddress()+"下线了\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
