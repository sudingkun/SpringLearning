package com.we.springboot.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author sudingkun
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户年龄
     */
    private Integer age;
}