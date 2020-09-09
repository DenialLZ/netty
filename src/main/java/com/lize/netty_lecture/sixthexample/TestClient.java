package com.lize.netty_lecture.sixthexample;

import com.lize.netty_lecture.thirdexample.MyChatClientInItializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author: lize
 * @Date: 2020/7/15 10:58
 * @Description:
 */
public class TestClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new TestClientInitializer());

            ChannelFuture channelFuture=bootstrap.connect("127.0.0.1",8080).sync();
            channelFuture.channel().closeFuture().sync();


        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
