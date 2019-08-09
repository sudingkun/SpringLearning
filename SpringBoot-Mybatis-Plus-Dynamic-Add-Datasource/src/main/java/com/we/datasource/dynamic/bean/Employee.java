package com.we.datasource.dynamic.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "employee")
public class Employee {
    /**
     * 员工id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 员工姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 员工年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 工资
     */
    @TableField(value = "salary")
    private BigDecimal salary;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    private String deptId;

}