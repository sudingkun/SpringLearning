package com.we.springboot.mqtt.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author we
 */
@Slf4j
@Component
public class MqttPredicate {

    private static final String TOPIC = "topic";

    public Boolean test(MqttEvent event) {
        log.info("event {}", event);
        return TOPIC.equals(event.getTopic());
    }


}
