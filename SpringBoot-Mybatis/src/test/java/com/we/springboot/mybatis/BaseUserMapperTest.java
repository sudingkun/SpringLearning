package com.we.springboot.mybatis;

import com.we.springboot.mybatis.bean.User;
import com.we.springboot.mybatis.dao.BaseUserMapper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis 通用mapper使用
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseUserMapperTest {

    @Autowired
    BaseUserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setId(1);
        user.setName("testMybatis");

        // 会把id一起插进去，当id重复时会报错
        // int result = userMapper.insertSelective(user);

        // 使用主键自增
        int result = userMapper.insertUseGeneratedKeys(user);
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
        int result = userMapper.insertList(users);
        System.out.println(result);
    }


    @Test
    public void delete() {
        User user = new User();
        user.setId(2);
        user.setName("test");
        // 根据实体属性作为条件进行删除，查询条件使用等号
        // int result = userMapper.delete(user);

        // 根据主键字段进行删除，方法参数必须包含完整的主键属性
        int result = userMapper.deleteByPrimaryKey(user);
        System.out.println(result);
    }

    @Test
    public void get() {
        User user = new User();
        user.setId(2);
        user.setName("test");
        // 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
        // user = userMapper.selectOne(user);
        // 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
        user = userMapper.selectByPrimaryKey(user);
        System.out.println(user);
    }

    @Test
    public void getAll() {
        List<User> UserList = userMapper.selectAll();
        System.out.println(UserList);
    }

    @Test
    public void getByCondition() {
        Example example = new Example(User.class);
        example.createCriteria().andLike("name", "%test%").andGreaterThan("age", 18);
        List<User> UserList = userMapper.selectByExample(example);
        System.out.println(UserList);

    }

    @Test
    public void getByCondition2() {
        User user = new User();
        user.setName("testMybatis2");
        RowBounds page = new RowBounds(1, 2);
        List<User> UserList = userMapper.selectByRowBounds(user, page);
        System.out.println(UserList);

    }

    @Test
    public void update() {
        User user = new User(1, "mybatisTest", 20);
        int result = userMapper.updateByPrimaryKeySelective(user);
        System.out.println(result);
    }


}