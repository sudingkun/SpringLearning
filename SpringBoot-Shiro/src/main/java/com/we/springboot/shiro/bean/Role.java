package com.we.springboot.shiro.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author sudingkun
 * @create 2019/8/19 15:25
 */
@Data
@TableName(value = "shiro_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    private Long parentId;


    private String name;


    private String enName;

}