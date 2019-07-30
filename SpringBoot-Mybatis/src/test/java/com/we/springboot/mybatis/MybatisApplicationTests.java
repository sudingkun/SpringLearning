package com.we.springboot.mybatis;

import com.github.pagehelper.PageHelper;
import com.we.springboot.mybatis.bean.User;
import com.we.springboot.mybatis.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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
    @Resource
    UserMapper userMapper;

   /* //使用注解方式
    @Resource(name = "userAnnotationsMapper")
    UserAnnotationsMapper userMapper;*/

    @Test
    public void insert() {
        User use = new User(1, "testMybatis", 20);
        int result = userMapper.insert(use);
        System.out.println(result);
    }
    @Test
    public void BatchInsert() {
        User use1 = new User(1, "testMybatis1", 21);
        User use2 = new User(2, "testMybatis2", 22);
        User use3 = new User(3, "testMybatis3", 23);
        List<User> users = new ArrayList<>();
        users.add(use1);
        users.add(use2);
        users.add(use3);
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
        User use = userMapper.getById(1);
        System.out.println(use);
    }

    @Test
    public void getAll() {
        List<User> UserList = userMapper.getAll();
        System.out.println(UserList);
    }

    @Test
    public void getByCondition() {
        PageHelper.startPage(1, 2);
        User User = new User();
        User.setName("tom");
        List<User> UserList = userMapper.getByCondition(User);
        System.out.println(UserList);

    }

    @Test
    public void getByNameAndAgeBetween() {
        List<User> UserList = userMapper.getByNameAndAgeBetween("tom", 1, 2);
        System.out.println(UserList);

    }

    @Test
    public void update() {
        User User = new User(1, "mybatisTest", 20);
        int result = userMapper.update(User);
        System.out.println(result);
    }


}
