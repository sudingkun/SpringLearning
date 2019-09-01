package com.we.springboot.mongodb;

import com.we.springboot.mongodb.bean.Student;
import com.we.springboot.mongodb.service.MongoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTemplateTests {

    @Autowired
    private MongoService mongoService;

    @Test
    public void saveObj() {
        Student student = new Student();
        student.setId(2L);
        student.setName("test2");
        student.setAge(20);
        student.setHobbies(Arrays.asList("sing", "jump", "rap"));
        System.out.println(mongoService.save(student));
    }

    @Test
    public void findAll() {
        System.out.println(mongoService.findAll());
    }

    @Test
    public void findOne() {
        Long id = 2L;
        System.out.println(mongoService.getStudentById(id));
    }

    @Test
    public void findOneByName() {
        String name = "test2";
        System.out.println(mongoService.getStudentByName(name));
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setId(2L);
        student.setName("test2222");
        student.setAge(20);
        student.setHobbies(Arrays.asList("sing", "jump", "rap"));
        System.out.println(mongoService.update(student));
    }

    @Test
    public void delete() {
        Student student = new Student();
        student.setId(2L);
        student.setAge(20);
        System.out.println(mongoService.delete(student));
    }

    @Test
    public void deleteById() {
        Long id = 2L;
        System.out.println(mongoService.deleteStudentById(id));
    }
}
