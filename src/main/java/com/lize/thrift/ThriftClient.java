package com.lize.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author: lize
 * @Date: 2020/8/5 16:06
 * @Description:
 */
public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport = new TFramedTransport(new TSocket("127.0.0.1", 8899), 600);
        TProtocol tProtocol = new TCompactProtocol(tTransport);
        PersonService.Client client=new PersonService.Client(tProtocol);

        try {
            tTransport.open();
            Person person = client.getPersonByUsername("李四");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarride());

            Person person1 = new Person();
            person1.setUsername("张三");
            person1.setAge(2222);
            person1.setMarride(true);

            client.savePerson(person1);

        }catch (Exception ec){
            throw new RuntimeException(ec.getMessage(),ec);
        }finally {
            tTransport.close();
        }
    }

}
