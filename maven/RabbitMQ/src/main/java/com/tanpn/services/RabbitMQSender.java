package com.tanpn.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tanpn.mdl.SimpleMessage;

@Service
public class RabbitMQSender {

    private AmqpTemplate amqpTemplate;

    @Autowired
    // @Qualifier("rabbitJsonTemplate")
    public RabbitMQSender(@Qualifier("rabbitJsonTemplate") AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

	@Value("${mq.rabbitmq.exchange}")
	String exchange;

	@Value("${mq.rabbitmq.routingkey}")
	private String routingkey;

	@Value("${mq.rabbitmq.routingkey.json}")
	private String routingkeyJson;
	
	public void send(String message) {
        if (amqpTemplate instanceof RabbitTemplate) {
            final String lvConverterClassName = ((RabbitTemplate) amqpTemplate).getMessageConverter().getClass().getSimpleName();
            System.out.println("Using " + lvConverterClassName);
            if (lvConverterClassName.equals("SimpleMessageConverter")) {
		        amqpTemplate.convertAndSend(exchange, routingkey, message);
            }
            else {
                SimpleMessage lvSimpleMessage = new SimpleMessage();
                lvSimpleMessage.setKey("cc");
                lvSimpleMessage.setValue(message);
                this.send(lvSimpleMessage);
            }
        }
		System.out.println("Send msg = " + message);
	}
	
	public void send(SimpleMessage message) {
		amqpTemplate.convertAndSend(exchange, routingkeyJson, message);
		System.out.println("Send to " + routingkeyJson + " msg = " + message.getKey());
	}
}
