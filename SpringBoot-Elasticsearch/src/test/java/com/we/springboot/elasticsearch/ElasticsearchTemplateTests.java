package com.we.springboot.elasticsearch;

import com.we.springboot.elasticsearch.bean.Student;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTemplateTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("test");
        student.setAge(20);
        student.setHobbies(Arrays.asList("sing", "jump", "rap"));

        IndexQuery userQuery = new IndexQuery();
        userQuery.setObject(student);
        System.out.println(elasticsearchTemplate.index(userQuery));
    }

    @Test
    public void deleteStudent() {
        System.out.println(elasticsearchTemplate.delete(Student.class, Long.toString(1L)));
    }

    @Test
    public void findAll() {
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery());
        System.out.println(elasticsearchTemplate.queryForList(searchQuery, Student.class));
    }

    @Test
    public void findByName() {
        String name = "test";
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchQuery("name", name));
        System.out.println(elasticsearchTemplate.queryForList(searchQuery, Student.class));
    }


}
