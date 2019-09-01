package com.we.springboot.mongodb;

import com.we.springboot.mongodb.bean.Student;
import com.we.springboot.mongodb.dao.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveObj() {
        Student student = new Student();
        student.setId(2L);
        student.setName("test2");
        student.setAge(20);
        student.setHobbies(Arrays.asList("sing", "jump", "rap"));
        System.out.println(studentRepository.save(student));
    }

    @Test
    public void findAll() {
        System.out.println(studentRepository.findAll());
    }

    @Test
    public void findOne() {
        Long id = 2L;
        System.out.println(studentRepository.findById(id).orElse(null));
    }

    @Test
    public void findOneByName() {
        String name = "test2";
        System.out.println(studentRepository.findByName(name));
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setId(2L);
        student.setName("test222");
        System.out.println(studentRepository.save(student));
    }

    @Test
    public void delete() {
        Student student = new Student();
        student.setId(2L);
        student.setAge(20);
        studentRepository.delete(student);
    }

    @Test
    public void deleteById() {
        Long id = 2L;
        studentRepository.deleteById(id);
    }
}
