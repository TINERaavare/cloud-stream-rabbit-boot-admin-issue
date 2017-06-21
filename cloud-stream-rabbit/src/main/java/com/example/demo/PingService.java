package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PingService {

    private MessageChannels messageChannels;

    @Autowired
    public PingService(MessageChannels messageChannels) {
        this.messageChannels = messageChannels;
    }

    @Scheduled(fixedRate = 1000)
    private void ping() {
        messageChannels.pingOutput().send(MessageBuilder.withPayload("Hello").build());
    }

}
