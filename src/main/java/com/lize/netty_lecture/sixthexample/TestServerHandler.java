package com.lize.netty_lecture.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: lize
 * @Date: 2020/7/15 10:36
 * @Description:
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyMessageMyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageMyDataInfo.MyMessage msg) throws Exception {

        MyMessageMyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        System.out.println("枚举类型===="+dataType.getNumber());
        if (MyMessageMyDataInfo.MyMessage.DataType.PersonType == dataType) {
            MyMessageMyDataInfo.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAddress());
            System.out.println(person.getAge());
        } else if (MyMessageMyDataInfo.MyMessage.DataType.DogType == dataType) {
            MyMessageMyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        } else {
            MyMessageMyDataInfo.Cat dog = msg.getCat();
            System.out.println(dog.getName());
            System.out.println(dog.getCity());
        }


//        System.out.println(msg.getName());
//        System.out.println(msg.getAge());
//        System.out.println(msg.getAddress());
    }
}
