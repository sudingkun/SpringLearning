package com.we.springboot.kafka.consumer;


import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * @author we
 */
@Component
@Log4j2
public class FileConsumer {

    private void receive(ConsumerRecord<String, byte[]> record, int partitions) throws IOException {
        log.info("partitions " + partitions + "  ");
        FileOutputStream outputStream = new FileOutputStream(new File("C:/Users/welov/Desktop", "pic.jpg"));
        Optional<byte[]> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            byte[] message = record.value();
            outputStream.write(message);
        }
        outputStream.close();
    }

    @KafkaListener(id = "0", topicPartitions = {@TopicPartition(topic = "file", partitions = {"0"})})
    public void receiveMessage(ConsumerRecord<String, byte[]> record) throws IOException {
        receive(record, 0);
    }

    @KafkaListener(id = "1", topicPartitions = {@TopicPartition(topic = "file", partitions = {"1"})})
    public void receiveMessage2(ConsumerRecord<String, byte[]> record) throws IOException {
        receive(record, 2);
    }

}

