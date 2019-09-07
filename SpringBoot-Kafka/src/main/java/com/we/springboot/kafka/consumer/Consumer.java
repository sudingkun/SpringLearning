package com.we.springboot.kafka.consumer;


import com.we.springboot.kafka.bean.Message;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author we
 */
@Component
@Log4j2
public class Consumer {

    @KafkaListener(topics = "message")
    public void handleMessage(ConsumerRecord record) {
        String message = (String) record.value();
        log.info("收到消息: {}", message);
    }


    @KafkaListener(topics = "entity")
    public void listen(Message message) {
        log.info("接收对象: {}", message);
    }



}

