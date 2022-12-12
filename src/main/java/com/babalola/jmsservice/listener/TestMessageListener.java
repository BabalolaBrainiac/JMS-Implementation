package com.babalola.jmsservice.listener;

import com.babalola.jmsservice.config.JMSConfig;
import com.babalola.jmsservice.model.SampleMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TestMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.QUEUE)
    public void listen(@Payload SampleMessage sampleMessage, @Headers MessageHeaders messageHeaders, javax.jms.Message message) {
//
//
//        System.out.println("Message received");
//        System.out.println(sampleMessage);
    }

    @JmsListener(destination = JMSConfig.SEND_AND_RECEIVE_QUEUE)
    public void listenForMessage(@Payload SampleMessage sampleMessage, @Headers MessageHeaders messageHeaders,
                                 javax.jms.Message message) throws JMSException {

        SampleMessage payloadMessage = SampleMessage.builder()
                .messageId(UUID.randomUUID())
//                .messageContent(content)
                .messageContent("Listening for Receiving Messages")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMessage);


        System.out.println("Message Sent received");
        System.out.println("Message Received:" + message.toString());
    }
}
