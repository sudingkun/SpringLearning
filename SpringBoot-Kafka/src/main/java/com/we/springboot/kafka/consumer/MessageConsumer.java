package com.we.springboot.kafka.consumer;


import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author we
 */
@Component
@Log4j2
public class MessageConsumer {

    @KafkaListener(topics = "message")
    public void handleMessage(ConsumerRecord record) {
        String message = (String) record.value();
        log.info("收到消息: {}", message);
    }

}

