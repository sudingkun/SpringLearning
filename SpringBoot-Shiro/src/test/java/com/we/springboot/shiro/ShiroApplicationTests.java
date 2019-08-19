package com.we.springboot.shiro;

import com.we.springboot.shiro.bean.User;
import com.we.springboot.shiro.dao.UserMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {


    @Test
    public void contextLoads() {
    }

}
