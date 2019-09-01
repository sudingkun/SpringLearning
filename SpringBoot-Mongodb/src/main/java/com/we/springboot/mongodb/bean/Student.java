package com.we.springboot.mongodb.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * @author we
 */
@Data
@ToString
public class Student implements Serializable {
    @Id
    private Long id;

    private String name;

    private Integer age;

    private List<String> hobbies;
}
