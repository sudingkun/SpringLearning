package com.we.springboot.kafka.producer;

import com.we.springboot.kafka.bean.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

/**
 * @author WE
 */
@RestController
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, byte[]> byteKafkaTemplate;

    private final KafkaTemplate<String, Message> entityKafkaTemplate;


    private static final Integer SEND_COUNT = 2;

    private static File file;

    static {
        ClassPathResource resource = new ClassPathResource("file/壁纸.jpg");
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        byteKafkaTemplate.send("file", 0, "upload", in);
        return "send file success";
    }

    @RequestMapping("sendEntity")
    public String sendEntity() {
        entityKafkaTemplate.send("entity", new Message("test", new Date()));
        return "send entity success";
    }
}
