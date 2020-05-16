package com.we.springboot.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author we
 * 新引入了 Knife4j，配置的时候和正常的 swagger2 一样
 * 访问 http://ip:port/doc.html
 * https://doc.xiaominfo.com/knife4j/
 */
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@EnableSwagger2
@SpringBootApplication
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

}
