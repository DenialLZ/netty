package com.lize.netty_lecture.firstexamples;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author: lize
 * @Date: 2020/5/12 10:38
 * @Description:处理器
 */
public class TestHttpServerHadler extends SimpleChannelInboundHandler<HttpObject> {

    //读取客户端所发过来的请求，返回给客户端
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {

        System.out.println("msg对应类："+msg.getClass());

        System.out.println("netty请求远程地址："+channelHandlerContext.channel().remoteAddress());

        Thread.sleep(8000);
        if (msg instanceof HttpRequest) {

            System.out.println("请求channelRead0");

            HttpRequest httpRequest=(HttpRequest)msg;

            System.out.println("请求方法名："+httpRequest.method().name());

            URI url = new URI(httpRequest.uri());

            if ("/favicon.ico".equals(url.getPath())){
                System.out.println("请求favicon.ico");
                return;
            }


            //向客户端返回的内容
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");

            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            channelHandlerContext.writeAndFlush(response);
            channelHandlerContext.channel().close();

        }

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Active 活动状态");
        super.channelActive(ctx);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Registered 注册");
        super.channelRegistered(ctx);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Added 新通道添加");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Inactive 不活动状态");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Unregistered 取消注册");
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Removed 链接失去了");
        super.handlerRemoved(ctx);
    }
}
