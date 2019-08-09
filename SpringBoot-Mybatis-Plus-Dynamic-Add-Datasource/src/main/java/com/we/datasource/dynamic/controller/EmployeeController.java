package com.we.datasource.dynamic.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.we.datasource.dynamic.bean.Employee;
import com.we.datasource.dynamic.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getAll")
    public List<Employee> getAll() {
        return employeeService.list();
    }

    @GetMapping("getById/{id}")
    public Employee getById(@PathVariable String id) {
        String tableName = StringUtils.substringBefore(id, "_");
        return employeeService.getById(id, tableName);
    }


    @DS("#tableName")
    @PutMapping("save/{tableName}")
    public boolean save(@RequestBody Employee employee, @PathVariable String tableName) {
        return employeeService.save(employee);
    }

    /**
     * 动态添加druid数据源。添加的数据源没有配置文件配置的全局配置。
     */
    @GetMapping("addDataSource")
    public boolean addDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
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
