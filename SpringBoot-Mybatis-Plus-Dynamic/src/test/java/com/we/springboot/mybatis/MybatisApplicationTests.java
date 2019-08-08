package com.we.springboot.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.we.springboot.mybatis.bean.User;
import com.we.springboot.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void datasource(){
        System.out.println(dataSource);
    }


    @Autowired
    UserService userService;


    @Test
    public void save() {
        User user = new User(1, "testMybatis", 20);
        boolean result = userService.save(user);
        System.out.println(result);
    }

    @Test
    public void deleteById() {
        boolean result = userService.removeById(16);
        System.out.println(result);
    }

    @Test
    public void getByNameAndAgeBetween() {
        PageHelper.startPage(1, 2).setOrderBy("id");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().like("name", "dy")
                .between("age", 1, 20);
        List<User> users = userService.list(queryWrapper);
        System.out.println(users);
    }


    @Test
    public void update() {
        User user = new User();
        user.setId(1);
        userService.update(user, new UpdateWrapper<User>().set("name", "testDynamic").set("age", 666));
    }


}
