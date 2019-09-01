package com.we.springboot.elasticsearch;

import com.we.springboot.elasticsearch.bean.Student;
import com.we.springboot.elasticsearch.dao.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = new Student();
        student.setName("test2");
        student.setAge(22);
        student.setHobbies(Collections.singletonList("study"));
        System.out.println(studentRepository.save(student));
    }

    @Test
    public void deleteById() {
        studentRepository.deleteById(1L);
    }

    @Test
    public void findAll() {
        System.out.println(studentRepository.findAll());
    }

    @Test
    public void findOne() {
        System.out.println(studentRepository.findById(1L).orElse(null));
    }

    @Test
    public void findOneByAge() {
        System.out.println(studentRepository.findByAgeBetween(1, 30));
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setId(1L);
        student.setName("tom");
        student.setAge(22);
        student.setHobbies(Collections.singletonList("sing"));
        System.out.println(studentRepository.save(student));
    }


}
