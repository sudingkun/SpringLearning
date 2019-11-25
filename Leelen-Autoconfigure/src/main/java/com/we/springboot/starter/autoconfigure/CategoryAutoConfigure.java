package com.we.springboot.starter.autoconfigure;

import com.we.springboot.starter.bean.BaseCategory;
import com.we.springboot.starter.handler.CategoryInvocation;
import com.we.springboot.starter.mapper.BaseCategoryRepository;
import com.we.springboot.starter.mapper.CategoryMapper;
import com.we.springboot.starter.properties.CategoryProperties;
import com.we.springboot.starter.service.CategoryService;
import com.we.springboot.starter.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import static com.we.springboot.starter.commons.LeelenCommons.*;


/**
 * @author we
 */
@EnableConfigurationProperties(CategoryProperties.class)
@ConditionalOnProperty(prefix = CATEGORY, name = ENABLED, havingValue = TRUE, matchIfMissing = true)
public class CategoryAutoConfigure {

    @Bean
    public CategoryService<BaseCategory> categoryService() {
        return new CategoryServiceImpl<>();
    }


}
