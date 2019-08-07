package com.we.springboot.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * 这里使用了xml和注解的方式测试。
 * 需要xml测试就把注解方式注释起来
 * 需要注解测试就把xml方式注释起来
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource(){
        System.out.println(dataSource);
    }

}
