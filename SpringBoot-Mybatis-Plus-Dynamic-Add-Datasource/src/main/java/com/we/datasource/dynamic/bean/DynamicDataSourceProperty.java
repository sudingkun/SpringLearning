package com.we.datasource.dynamic.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: sudingkun
 * @date: 2019-08-15 12:15
 */
@Data
@AllArgsConstructor
@TableName(value = "db")
public class DynamicDataSourceProperty  {

    private String name;

    private String username;

    private String password;

    private String url;

    private String driverClassName;

}
