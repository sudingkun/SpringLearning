package com.we.springboot.mongodb.dao;

import com.we.springboot.mongodb.bean.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author we
 */
@Repository("mongoRepository")
public interface StudentRepository extends MongoRepository<Student, Long> {

    /**
     * 通过姓名找学生
     * @param name 姓名
     * @return 对应姓名的学生
     */
    Student findByName(String name);
}
