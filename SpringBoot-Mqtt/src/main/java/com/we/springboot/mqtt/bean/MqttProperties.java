package com.we.springboot.mqtt.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author we
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private final MqttProperties.Producer producer = new MqttProperties.Producer();

    private final MqttProperties.Consumer consumer = new MqttProperties.Consumer();

    private String username;

    private String password;

    private String hostUrl;

    private Integer keepAlive;

    private Integer completionTimeout;

    public MqttProperties.Producer getProducer() {
        return this.producer;
    }

    public MqttProperties.Consumer getConsumer() {
        return this.consumer;
    }


    @Data
    public static class Producer {

        private String clientId;

        private String defaultTopic;

    }

    @Data
    public static class Consumer {

        private String clientId;

        private List<String> defaultTopic;

    }

}
