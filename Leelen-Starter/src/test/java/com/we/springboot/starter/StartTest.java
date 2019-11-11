package com.we.springboot.starter;

import com.we.springboot.starter.service.CategoryService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StartTest {
    @Autowired
    private CategoryService categoryService;

    @org.junit.Test
    public void contextLoads() {
        categoryService.list();
    }

}
