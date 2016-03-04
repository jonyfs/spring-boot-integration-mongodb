package br.com.jonyfs.spring.boot.integration.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;


@SpringBootApplication
@IntegrationComponentScan
public class Application implements CommandLineRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    @Qualifier("priorityChannel")
    private MessageChannel priorityChannel;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Starting...");
        Message<String> message = MessageBuilder.withPayload("1").setPriority(1).build();
        this.priorityChannel.send(message);
        LOGGER.info("Finished.");
    }

}