package com.lize.netty_lecture.sixthexample;

import com.lize.netty_lecture.fourthexample.MyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: lize
 * @Date: 2020/7/15 10:31
 * @Description:
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGrouup= new NioEventLoopGroup();
        EventLoopGroup workerGroup= new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGrouup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture=serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGrouup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
