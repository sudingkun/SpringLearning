package com.we.springboot.shiro;

import org.crazycake.shiro.RedisManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {

    @Resource
    private RedisManager redisManager;

    @Test
    public void contextLoads() {
        System.out.println(redisManager);
    }

}
