package com.we.springboot.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.we.springboot.mybatis.bean.User;
import com.we.springboot.mybatis.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {


    @Autowired
    UserMapper userMapper;


    @Test
    public void insert() {
        User user = new User(1, "testMybatis", 20);
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void deleteById() {
        int result = userMapper.deleteById(11);
        System.out.println(result);
    }

    @Test
    public void getById() {
        User user = userMapper.selectById(12);
        System.out.println(user);
    }

    @Test
    public void getAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void getByCondition() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().ge("age", 1);
        Page<User> page = new Page<>(1, 2);
        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);
        System.out.println(userPage.getRecords());

    }

    @Test
    public void getByNameAndAgeBetween() {
        PageHelper.startPage(1, 2).setOrderBy("id");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().like("name", "tom")
                .between("age", 1, 10);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);

    }

    @Test
    public void update() {
        User user = new User(1, "mybatisTest", 20);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }


}
