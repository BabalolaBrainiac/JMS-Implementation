package com.babalola.jmsservice.sender;

import com.babalola.jmsservice.config.JMSConfig;
import com.babalola.jmsservice.model.SampleMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Component
public class TestMessageSender {
    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
//
//        System.out.println("Message Sender Triggered");

        SampleMessage newMessage = SampleMessage.builder()
                .messageId(UUID.randomUUID())
//                .messageContent(content)
                .messageContent("Testing Sending Message")
                .build();

//        jmsTemplate.convertAndSend(JMSConfig.QUEUE, newMessage);
//        System.out.println("Message Sent to Queue");
    }


    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {
        System.out.println("Message Sender Triggered");

        SampleMessage message = SampleMessage.builder()
                .messageId(UUID.randomUUID())
//                .messageContent(content)
                .messageContent("Sending Message")
                .build();

        Message sentMessage = jmsTemplate.sendAndReceive(JMSConfig.SEND_AND_RECEIVE_QUEUE, session -> {
            Message newMessage = null;

            try {
                newMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                newMessage.setStringProperty("_type", "com.babalola.jmsservice.model.SampleMessage");

                System.out.println("Message Converted and Sending");
                return newMessage;
            } catch (JsonProcessingException e) {
                throw new JMSException("New JMS Exception");
            }
        });
        assert sentMessage != null;
        System.out.println( "Sent Message:" + sentMessage.getBody(String.class));
    }
}
