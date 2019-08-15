package com.we.datasource.dynamic.controller;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import com.we.datasource.dynamic.bean.Employee;
import com.we.datasource.dynamic.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

/**
 * @author: sudingkun
 * @date: 2019-08-08 16:07
 */
@RestController
@RequestMapping("emp")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    private final DynamicRoutingDataSource dynamicRoutingDataSource;

    private final DynamicDataSourceProperties dynamicDataSourceProperties;

    @GetMapping("getAll")
    public List<Employee> getAll() {
        return employeeService.list();
    }

    @GetMapping("getById/{id}")
    public Employee getById(@PathVariable String id) {
        String tableName = StringUtils.substringBeforeLast(id, "_");
        return employeeService.getById(id, tableName);
    }


    @DS("#tableName")
    @PutMapping("save/{tableName}")
    public boolean save(@RequestBody Employee employee, @PathVariable String tableName) {
        return employeeService.save(employee);
    }

    /**
     * 动态添加druid数据源。
     * todo 这里是写死的，实际需要在入参传入一个DruidDataSource（自定义druid属性），并把这个保存到数据库。
     */
    @GetMapping("addDataSource")
    public boolean addDataSource() {
        //创建一个druid数据源
        DruidDataSource druidDataSource = new DruidDataSource();

        //获取全局的druid配置
        DruidConfig globalConfig = dynamicDataSourceProperties.getDruid();

        //创建一个druid配置
        DruidConfig druidConfig = new DruidConfig();

        //把全局的druid配置转换成属性
        Properties properties = druidConfig.toProperties(globalConfig);

        //设置全局属性
        druidDataSource.setConnectProperties(properties);

        //设置自定义属性
        druidDataSource.setUrl("jdbc:mysql://47.106.95.195:3306/spring?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setInitialSize(4);
        druidDataSource.setMinIdle(4);
        druidDataSource.setMaxActive(15);
        dynamicRoutingDataSource.addDataSource("addTest", druidDataSource);
        return true;
    }

}
