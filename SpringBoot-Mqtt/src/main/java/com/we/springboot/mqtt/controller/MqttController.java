package com.we.springboot.mqtt.controller;

import com.we.springboot.mqtt.producer.MqttGateway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author we
 */
@RestController
public class MqttController {

    @Resource
    private MqttGateway mqttGateway;

    @RequestMapping("mqtt/send")
    public String send(){
        mqttGateway.sendDefaultTopicToMqtt("test");
        return "success";
    }

}
