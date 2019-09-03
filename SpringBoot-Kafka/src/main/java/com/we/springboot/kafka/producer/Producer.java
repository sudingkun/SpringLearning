package com.we.springboot.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author WE
 */
@RestController
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, byte[]> byteKafkaTemplate;

    private static final Integer SEND_COUNT = 2;

    private static File file = new File("C:/Users/welov/Pictures/壁纸/壁纸002-2.jpg");

    @RequestMapping("sendMsg")
    public String sendMessage() {
        for (int i = 0; i < SEND_COUNT; i++) {
            kafkaTemplate.send("message", "value_" + i);
        }
        return "send massage success";
    }

    @RequestMapping("sendFile")
    public String sendFile() throws IOException {
        byte[] in = Files.readAllBytes(file.toPath());
        for (int i = 0; i < SEND_COUNT; i++) {
            byteKafkaTemplate.send("file", i, "upload", in);
        }
        return "send file success";
    }
}
