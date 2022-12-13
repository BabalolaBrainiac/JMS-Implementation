package com.babalola.jmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsServiceApplication {

    public static void main(String[] args) throws Exception {
//        ActiveMQServer newServer = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//                .setPersistenceEnabled(false)
//                .setJournalDirectory("target/data/journal")
//                .setSecurityEnabled(false)
//                .addAcceptorConfiguration("invm", "vm://0"));
//
//        newServer.start();

        SpringApplication.run(JmsServiceApplication.class, args);

    }
}
