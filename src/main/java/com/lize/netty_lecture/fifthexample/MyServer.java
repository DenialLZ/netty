package com.lize.netty_lecture.fifthexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author: lize
 * @Date: 2020/7/9 15:16
 * @Description:WebSocket建立长连接
 */
public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGrouup= new NioEventLoopGroup();
        EventLoopGroup workerGroup= new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGrouup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new WebSocketChannelInitializer());

            ChannelFuture channelFuture=serverBootstrap.bind(new InetSocketAddress(8080)).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGrouup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
