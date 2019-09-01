package com.we.springboot.elasticsearch.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author WE
 */
@Data
@ToString
@Document(indexName = "person", type = "student")
public class Student implements Serializable {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("hobbies")
    private List<String> hobbies;
}
