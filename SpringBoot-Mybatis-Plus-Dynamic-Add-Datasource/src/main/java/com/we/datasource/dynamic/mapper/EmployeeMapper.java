package com.we.datasource.dynamic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.datasource.dynamic.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}