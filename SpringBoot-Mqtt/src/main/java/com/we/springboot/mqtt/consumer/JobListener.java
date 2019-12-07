package com.we.springboot.mqtt.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * @author we
 */
@Slf4j
@Component
public class JobListener {

    @EventListener
    public void receive(MqttEvent mqttEvent) {
        System.out.println(mqttEvent);
        log.info("接到消息1：" + mqttEvent.getMessage());
    }

    @EventListener(condition = "@mqttPredicate.test(#mqttEvent)")
    public void receive2(MqttEvent mqttEvent) {
        log.info("接到消息2：" + mqttEvent.getMessage());
    }


}
