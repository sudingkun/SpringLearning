package com.we.springboot.starter.bean;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 */
@Data
public class BaseCategoryTree implements Serializable {

    private static final long serialVersionUID = 8774086808039793683L;

    private String path;

    private String component;

    private Boolean left;

    private Boolean alwaysShow;

    private Meta meta = new Meta();

    private List<BaseCategory> children = new ArrayList<>();

    @Data
    public static class Meta {
        /**
         * 菜单名
         */
        private String title;

        /**
         * 菜单图标
         */
        private String icon;
    }
}
