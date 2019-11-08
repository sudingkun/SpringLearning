package com.we.springboot.rabbitmq.consumer;

import com.we.springboot.rabbitmq.bean.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * rabbitmq消费者
 *
 * @author we
 */
@Component
public class RabbitMqConsumer {

    @RabbitListener(queues = "employee")
    public void rabbitMqUpdate(Employee employee) {
        System.out.println("consume " + employee);
    }
}
