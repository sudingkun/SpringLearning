package com.we.springboot.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.we.springboot.starter.commons.LeelenCommons.CATEGORY_TABLE_NAME;

/**
 * @author welov
 */
@Data
@ConfigurationProperties(prefix = CATEGORY_TABLE_NAME)
public class CategoryProperties {

    private Boolean enabled = false;

    public static String categoryTableName = "category";

}
