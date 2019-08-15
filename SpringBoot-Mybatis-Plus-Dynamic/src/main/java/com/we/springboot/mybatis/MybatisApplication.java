package com.we.springboot.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * config和 handler包底下的mybatis plus配置(不是必须配置的)
 * @author sudingkun
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableTransactionManagement
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }


}
