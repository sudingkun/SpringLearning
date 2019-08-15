package com.we.springboot.redis.service.Impl;

import com.we.springboot.redis.bean.Department;
import com.we.springboot.redis.dao.DepartmentRepository;
import com.we.springboot.redis.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dept")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;


    @Override
    @Caching(put = @CachePut(key = "#result.id"),
            evict = @CacheEvict(cacheNames = "depts", allEntries = true))
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    @Caching(evict = {@CacheEvict(key = "#id"),
            @CacheEvict(cacheNames = "depts", allEntries = true)})
    public void delete(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    @Cacheable(cacheNames = "depts", unless = "#result == null")
    public List<Department> findAll() {
        System.out.println("findAll");
        return departmentRepository.findAll();
    }


    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Department findById(Integer id) {
        System.out.println("部门id " + id);
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    @Caching(put = @CachePut(key = "#department.id"),
            evict = @CacheEvict(cacheNames = "depts", allEntries = true))
    public Department update(Department department) {
        return departmentRepository.save(department);
    }
}
