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
 * 下面的3个template:（可以自己自定义序列化和反序列化）
 *      String 是基本的消息发送，对应的序列化和反序列化：
 *          org.apache.kafka.common.serialization.StringSerializer
 *          org.apache.kafka.common.serialization.StringDeserializer
 *      byte[] 可以发送任何类型，对应的序列化和反序列化：
 *          org.apache.kafka.common.serialization.ByteArraySerializer
 *          org.apache.kafka.common.serialization.ByteArrayDeserializer
 *      Message 可以发送对象，对应的序列化和反序列化：
 *          org.springframework.kafka.support.serializer.JsonSerializer
 *          org.springframework.kafka.support.serializer.JsonDeserializer
 *  这里的配置都是配置文件中配的，只能配置一个，每次也只能用一个。如果同时需要多个template，需要自己写配置类。
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

    /**
     * 发送小文件不需要设置
     * 发送大文件需要设置application.yml里面的，还需要设置kafka服务器server.properties
     */
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
