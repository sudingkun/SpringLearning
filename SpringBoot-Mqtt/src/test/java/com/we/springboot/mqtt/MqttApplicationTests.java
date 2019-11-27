package com.we.springboot.mqtt;

import com.we.springboot.mqtt.producer.MqttGateway;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
class MqttApplicationTests {
    @Resource
    private MqttGateway mqttGateway;

    @Test
    public void send() {
        mqttGateway.sendDefaultTopicToMqtt("test mqtt");

    }

}
