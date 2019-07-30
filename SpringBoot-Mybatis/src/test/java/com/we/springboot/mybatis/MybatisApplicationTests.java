package com.we.springboot.mybatis;

import com.github.pagehelper.PageHelper;
import com.we.springboot.mybatis.bean.User;
import com.we.springboot.mybatis.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    public void insert() {
        User use = new User(1, "testMybatis", 20);
        int result = userMapper.insert(use);
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
    public void update() {
        User User = new User(1, "mybatisTest", 20);
        int result = userMapper.update(User);
        System.out.println(result);
    }


}
