package com.we.springboot.mybatis.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author sudingkun
 */
@TableName("user")
@Data
@ToString
@NoArgsConstructor
public class User {

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户年龄
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer age;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer deleted;


}