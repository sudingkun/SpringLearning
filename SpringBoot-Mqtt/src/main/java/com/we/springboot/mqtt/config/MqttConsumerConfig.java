package com.we.springboot.mqtt.config;

import com.we.springboot.mqtt.bean.MqttProperties;
import com.we.springboot.mqtt.consumer.MqttEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * @author we
 */
@Data
@Slf4j
@Configuration
public class MqttConsumerConfig {


    @Resource
    private MqttProperties mqttProperties;

    /**
     * @return Mqtt连接选项
     */
    @Bean("consumerConnectOptions")
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttProperties.getUsername());
        mqttConnectOptions.setPassword(mqttProperties.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{mqttProperties.getHostUrl()});
        mqttConnectOptions.setKeepAliveInterval(mqttProperties.getKeepAlive());
        return mqttConnectOptions;
    }

    /**
     * @return Mqtt 客户工厂
     */
    @Bean("consumerClientFactory")
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    /**
     * @return Mqtt 接收消息的通道
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getConsumer().getClientId(), mqttClientFactory());
        //设置主题
        mqttProperties.getConsumer().getDefaultTopic().forEach(adapter::addTopic);
        adapter.setCompletionTimeout(mqttProperties.getCompletionTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        //设置统一的服务器质量
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 事件触发
     */
    @Resource
    private ApplicationEventPublisher eventPublisher;

    /**
     * mqtt接收到消息后可以会调用这个方法
     * MqttEvent 为自定义事件，继承 ApplicationEvent，然后编写一个监听事件监听MyEvent，触发做出对应的操作。
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            MessageHeaders headers = message.getHeaders();
            String topic = (String) headers.get("mqtt_receivedTopic");
            //触发事件
            log.info("headers:   " + headers + "    data:   " + message.getPayload());
            eventPublisher.publishEvent(new MqttEvent(this, topic, message.getPayload().toString()));
        };
    }

}