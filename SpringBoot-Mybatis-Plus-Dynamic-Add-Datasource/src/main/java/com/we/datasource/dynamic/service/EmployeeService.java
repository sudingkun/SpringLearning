package com.we.datasource.dynamic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.we.datasource.dynamic.bean.Employee;
import com.we.datasource.dynamic.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Author admin
 * @create 2019/8/8 16:05
 */
@Service
public class EmployeeService extends ServiceImpl<EmployeeMapper, Employee> {

    @DS("#tableName")
    public Employee getById(Serializable id, String tableName) {
        return super.getById(id);
    }

    @DS("slave")
    @Override
    public List<Employee> list() {
        return super.list();
    }
}
