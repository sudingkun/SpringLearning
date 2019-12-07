package com.we.springboot.mqtt.producer;


import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;


/**
 * @author we
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    void sendDefaultTopicToMqtt(String data);

    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

}
