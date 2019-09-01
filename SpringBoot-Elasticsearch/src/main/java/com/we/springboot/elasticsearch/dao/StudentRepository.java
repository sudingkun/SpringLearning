package com.we.springboot.elasticsearch.dao;

import com.we.springboot.elasticsearch.bean.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WE
 */
@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, Long> {

    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);
}
