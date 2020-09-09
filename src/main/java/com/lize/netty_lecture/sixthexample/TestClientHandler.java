package com.lize.netty_lecture.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author: lize
 * @Date: 2020/7/15 11:01
 * @Description:
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyMessageMyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,MyMessageMyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //随机函数
        int randonInt = new Random().nextInt(2);


        MyMessageMyDataInfo.MyMessage myMessageBuild = null;

        if (randonInt == 0) {
            myMessageBuild = MyMessageMyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyMessageMyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(
                            MyMessageMyDataInfo.Person.newBuilder().setName("王五")
                                    .setAddress("随便").setAge(23).build()
                    ).build();
        } else if (randonInt == 1) {
            myMessageBuild = MyMessageMyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyMessageMyDataInfo.MyMessage.DataType.DogType)
                    .setDog(
                            MyMessageMyDataInfo.Dog.newBuilder().setName("二狗子")
                                    .setAge(2300).build()
                    ).build();
        } else {
            myMessageBuild = MyMessageMyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyMessageMyDataInfo.MyMessage.DataType.CatType)
                    .setCat(
                            MyMessageMyDataInfo.Cat.newBuilder().setName("猫")
                                    .setCity("喵星人").build()
                    ).build();
        }


//        MyDataInfo.Person build = MyDataInfo.Person.newBuilder()
//                .setName("李四").setAge(8888).setAddress("地球").build();


        ctx.channel().writeAndFlush(myMessageBuild);
    }
}
