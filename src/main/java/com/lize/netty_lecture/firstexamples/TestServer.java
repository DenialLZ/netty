package com.lize.netty_lecture.firstexamples;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: lize
 * @Date: 2020/5/11 17:06
 * @Description:第一步
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        //创建异步事件循环线程组（死循环） 接收客户端发起的连接，转给workgroup
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        //接收bossgroup分发的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //启动相应的服务端的代码
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossgroup,workerGroup).
                    channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());//定义自处理器

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossgroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }




    }
}
