package com.lize.netty_lecture.fourthexample;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author: lize
 * @Date: 2020/7/8 16:18
 * @Description:
 */
public class MyserverHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent=(IdleStateEvent) evt;

            String eventType=null;

            switch (idleStateEvent.state()){
                case READER_IDLE:
                    eventType="读空闲";
                    break;
                case WRITER_IDLE:
                    eventType="写空闲";
                    break;
                case ALL_IDLE:
                    eventType="读或者写空闲";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress()+"超时时间"+eventType);
            ctx.channel().close();

        }
    }
}
