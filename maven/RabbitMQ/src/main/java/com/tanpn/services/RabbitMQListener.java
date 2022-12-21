package com.tanpn.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.tanpn.mdl.SimpleMessage;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = "${mq.rabbitmq.queue}")
    public void receiveMessage(Object message) {
        System.out.println("Received Message:" + message.toString());
        System.out.println();
    }

    @RabbitListener(queues = {"${mq.rabbitmq.queue.json}"})
    public void consumeJsonMessage(SimpleMessage msg) {
        System.out.println(String.format("Received JSON message -> %s", msg.getValue().toString()));
        System.out.println();
    }
}
