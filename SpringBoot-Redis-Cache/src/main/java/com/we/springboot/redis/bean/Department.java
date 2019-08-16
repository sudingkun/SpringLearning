package com.we.springboot.redis.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Department {

    @Id
    private Integer id;

    private String departmentName;
}