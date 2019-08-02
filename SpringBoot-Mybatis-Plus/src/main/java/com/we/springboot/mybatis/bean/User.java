package com.we.springboot.mybatis.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author sudingkun
 * 开启AR模式需要继承Model
 */
@TableName("user")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class User extends Model<User> {

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