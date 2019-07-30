package com.we.springboot.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author sudingkun
 * @create 2019/7/30 19:16
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    private String name;

    private Integer age;
}