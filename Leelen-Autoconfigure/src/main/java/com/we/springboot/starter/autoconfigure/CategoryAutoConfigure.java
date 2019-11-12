package com.we.springboot.starter.autoconfigure;

import com.we.springboot.starter.interceptor.TableNameInterceptor;
import com.we.springboot.starter.properties.CategoryProperties;
import com.we.springboot.starter.service.CategoryService;
import com.we.springboot.starter.service.impl.CategoryServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

import static com.we.springboot.starter.commons.LeelenCommons.*;


/**
 * @author we
 */
@EnableConfigurationProperties(CategoryProperties.class)
@ConditionalOnProperty(prefix = CATEGORY_TABLE_NAME, name = ENABLED, havingValue = TRUE, matchIfMissing = true)
public class CategoryAutoConfigure {

    @Bean
    public TableNameInterceptor tableNameInterceptor() {
        TableNameInterceptor sqlStatsInterceptor = new TableNameInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        sqlStatsInterceptor.setProperties(properties);
        return sqlStatsInterceptor;
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }
}
