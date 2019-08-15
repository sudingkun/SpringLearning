package com.we.springboot.redis.service;

import com.we.springboot.redis.bean.Department;

import java.util.List;

public interface DepartmentService {

    Department save(Department department);

    void delete(Integer id);

    List<Department> findAll();

    Department  findById(Integer id);

    Department update(Department department);
}
