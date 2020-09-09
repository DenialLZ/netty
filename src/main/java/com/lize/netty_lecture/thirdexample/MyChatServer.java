package com.lize.netty_lecture.thirdexample;

import com.lize.netty_lecture.secondexample.MyClientInitidlizer;
import com.lize.netty_lecture.secondexample.MyServerInitislizer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: lize
 * @Date: 2020/5/14 16:55
 * @Description:服务器端-->初始化(MyClientInitidlizer)-->处理器(MyClientHandler)
 */
public class MyChatServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGrouup= new NioEventLoopGroup();
        EventLoopGroup workerGroup= new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGrouup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyClientInitidlizer());

            ChannelFuture channelFuture=serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGrouup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
