package com.we.springboot.mqtt.consumer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @author we
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MqttEvent extends ApplicationEvent {

    private String topic;

    private String message;

    public MqttEvent(Object source, String topic, String message) {
        super(source);
        this.topic = topic;
        this.message = message;
    }
}
