package com.we.springboot.rabbitmq.bean;

import lombok.Data;
import lombok.ToString;


/**
 * 员工实体类
 * @author we
 */
@Data
@ToString
public class Employee {

    private String id;
    private String name;
    private Integer age;
    private Double salary;
    private Integer deptId;
}
