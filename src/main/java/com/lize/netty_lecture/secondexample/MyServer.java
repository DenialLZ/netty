package com.lize.netty_lecture.secondexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: lize
 * @Date: 2020/5/14 16:05
 * @Description:
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGrouup= new NioEventLoopGroup();
        EventLoopGroup workerGroup= new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGrouup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitislizer());

            ChannelFuture channelFuture=serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGrouup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }
}
