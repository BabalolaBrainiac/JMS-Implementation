package com.babalola.jmsservice.sender;

import com.babalola.jmsservice.config.JMSConfig;
import com.babalola.jmsservice.model.SampleMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Data
@Component
public class TestMessageSender {
    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {

        System.out.println("Message Sender Triggered");

        SampleMessage newMessage = SampleMessage.builder()
                .messageId(UUID.randomUUID())
//                .messageContent(content)
                .messageContent("Testing Sending Message")
                .build();

        jmsTemplate.convertAndSend(JMSConfig.QUEUE, newMessage);
        System.out.println("Message Sent to Queue");
    }
}
