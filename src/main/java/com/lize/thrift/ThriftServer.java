package com.lize.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersonService;

/**
 * @author: lize
 * @Date: 2020/8/5 15:58
 * @Description:服务端
 */
public class ThriftServer {
    public static void main(String[] args) throws TTransportException {
        //一步非阻塞的
        TNonblockingServerSocket socket=new TNonblockingServerSocket(8899);
        //高可用的一个server
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        PersonService.Processor<PersonServiceImpl> personServiceProcessor = new PersonService.Processor<>(new PersonServiceImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(personServiceProcessor));

        THsHaServer tHsHaServer = new THsHaServer(arg);

        System.out.println("Thrift Server Strated!!!");

        tHsHaServer.serve();


    }
}
