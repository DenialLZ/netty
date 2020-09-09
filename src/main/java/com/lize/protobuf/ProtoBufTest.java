package com.lize.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author: lize
 * @Date: 2020/7/15 10:14
 * @Description:
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三").setAge(200000).setAddress("火星").build();//A服务器

        byte[] studentByteArray = student.toByteArray();//转换字节数组，就可以在网络传输。各语言之间可以尽心相互传递

        DataInfo.Student student1 = DataInfo.Student.parseFrom(studentByteArray);//将字节数组进行反序列化 B服务器

        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());

    }
}
