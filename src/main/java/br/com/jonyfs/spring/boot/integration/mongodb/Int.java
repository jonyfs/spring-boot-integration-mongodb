package br.com.jonyfs.spring.boot.integration.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

@Component
public class Int {
    @Autowired
    private ControlBusGateway controlBus;

    @Autowired
    @Qualifier("priorityChannel")
    private MessageChannel priorityChannel;

    @Autowired
    @Qualifier("priorityReplyChannel")
    private PollableChannel priorityReplyChannel;

    @MessagingGateway(defaultRequestChannel = "controlBus.input")
    private static interface ControlBusGateway {

        void send(String command);
    }

}
