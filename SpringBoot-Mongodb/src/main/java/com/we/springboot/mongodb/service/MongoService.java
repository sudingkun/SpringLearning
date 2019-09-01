package com.we.springboot.mongodb.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.we.springboot.mongodb.bean.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author we
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class MongoService {

    private final MongoTemplate mongoTemplate;

    public String save(Student student) {
        log.info("--------------------->[MongoDB save start]");
        Student result = mongoTemplate.save(student);
        return "添加成功 " + result;
    }

    public List<Student> findAll() {
        log.info("--------------------->[MongoDB find start]");
        return mongoTemplate.findAll(Student.class);
    }

    public Student getStudentById(Long id) {
        log.info("--------------------->[MongoDB find start]");
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Student.class);
    }

    public Student getStudentByName(String name) {
        log.info("--------------------->[MongoDB find start]");
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Student.class);
    }

    public String update(Student student) {
        log.info("--------------------->[MongoDB update start]");
        Query query = new Query(Criteria.where("_id").is(student.getId()));
        Update update = new Update().set("name", student.getName())
                .set("age", student.getAge())
                .set("hobbits", student.getHobbies());
        // updateFirst 更新查询返回结果集的第一条
        UpdateResult result = mongoTemplate.updateFirst(query, update, Student.class);

        // mongoTemplate.updateMulti(query,update,Student.class); 更新查询返回结果集的全部
        // mongoTemplate.upsert(query,update,Student.class); 更新对象不存在则去添加
        return "success " + result;
    }

    public String delete(Student student) {
        log.info("--------------------->[MongoDB delete start]");
        DeleteResult result = mongoTemplate.remove(student);
        return "success " + result;
    }

    public String deleteStudentById(Long id) {
        log.info("--------------------->[MongoDB delete start]");
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query, Student.class);
        return "success " + result;
    }
}
