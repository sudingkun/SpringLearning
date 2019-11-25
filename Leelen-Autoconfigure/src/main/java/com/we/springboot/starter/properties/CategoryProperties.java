package com.we.springboot.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.we.springboot.starter.commons.LeelenCommons.CATEGORY;


/**
 * @author welov
 */
@Data
@ConfigurationProperties(prefix = CATEGORY)
public class CategoryProperties {

    public Boolean enabled = false;

}
