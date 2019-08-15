package com.we.springboot.mybatis;

import com.github.pagehelper.PageHelper;
import com.we.springboot.mybatis.bean.User;
import com.we.springboot.mybatis.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里使用了xml和注解的方式测试。
 * 需要xml测试就把注解方式注释起来
 * 需要注解测试就把xml方式注释起来
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    //使用xml方式
    @Autowired
    UserMapper userMapper;

    /*//使用注解方式
    @Autowired
    UserAnnotationsMapper userMapper;*/


    @Test
    public void insert() {
        User user = new User(1, "testMybatis", 20);
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void BatchInsert() {
        User user1 = new User(1, "testMybatis1", 21);
        User user2 = new User(2, "testMybatis2", 22);
        User user3 = new User(3, "testMybatis3", 23);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        int result = userMapper.batchInsert(users);
        System.out.println(result);
    }


    @Test
    public void deleteById() {
        int result = userMapper.delete(1);
        System.out.println(result);
    }

    @Test
    public void getById() {
        User user = userMapper.getById(1);
        System.out.println(user);
    }

    @Test
    public void getAll() {
        List<User> users = userMapper.getAll();
        System.out.println(users);
    }

    @Test
    public void getByCondition() {
        PageHelper.startPage(1, 2);
        User user = new User();
        user.setName("test");
        List<User> users = userMapper.getByCondition(user);
        System.out.println(users);

    }

    @Test
    public void getByNameAndAgeBetween() {
        List<User> users = userMapper.getByNameAndAgeBetween("test", 1, 20);
        System.out.println(users);

    }

    @Test
    public void update() {
        User user = new User(1, "mybatisTest", 20);
        int result = userMapper.update(user);
        System.out.println(result);
    }


}
