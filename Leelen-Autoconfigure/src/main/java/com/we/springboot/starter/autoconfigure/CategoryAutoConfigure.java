package com.we.springboot.starter.autoconfigure;

import com.we.springboot.starter.properties.CategoryProperties;
import com.we.springboot.starter.service.CategoryService;
import com.we.springboot.starter.service.impl.CategoryServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static com.we.springboot.starter.commons.LeelenCommons.*;


/**
 * @author we
 */
@EnableConfigurationProperties(CategoryProperties.class)
@ConditionalOnProperty(prefix = CATEGORY_TABLE_NAME, name = ENABLED, havingValue = TRUE, matchIfMissing = true)
public class CategoryAutoConfigure {

    @Bean
    public CategoryService categoryService(){
        return new CategoryServiceImpl();
    }
}
