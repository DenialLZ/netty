package com.lize.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author: lize
 * @Date: 2020/8/5 15:23
 * @Description:
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String usernaem) throws DataException, TException {
        System.out.println("Got Client Param: "+usernaem );

        Person person = new Person();
        person.setUsername(usernaem);
        person.setAge(22);
        person.setMarride(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got Client Param");

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarride());
    }
}
