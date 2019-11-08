package com.we.springboot.rabbitmq.producer;


import com.we.springboot.rabbitmq.bean.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rabbitmq生产者
 *
 * @author we
 */
@RestController
@RequiredArgsConstructor
public class RabbitMqProducer {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String sendMessage() {
        Employee employee = new Employee();
        employee.setId("id");
        employee.setName("name");
        employee.setAge(18);
        employee.setSalary(10000.0D);
        employee.setDeptId(1);

        rabbitTemplate.convertAndSend("assess.direct", "employee", employee);
        return "send success";

    }
}
