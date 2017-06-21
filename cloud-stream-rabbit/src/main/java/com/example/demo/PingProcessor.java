package com.example.demo;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
public class PingProcessor {

    @ServiceActivator(inputChannel = "pingInput")
    public void processPing(Message<String> message) {
        System.out.println(message.getPayload());
    }

}
