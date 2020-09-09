package com.lize.netty_lecture.firstexamples;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: lize
 * @Date: 2020/5/12 10:33
 * @Description:初始化管道，channel被注册方法会被调用
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline=socketChannel.pipeline();

        //通道处理器
        pipeline.addLast("httpserverCode",new HttpServerCodec());//解码和编码HttpServerCodec
        //或者自己定义的通道处理器并提供诶给testserver去调用
        pipeline.addLast("testHttpServerHadler",new TestHttpServerHadler());

    }
}
