package com.we.springboot.starter.service;


import com.we.springboot.starter.bean.BaseCategory;

import java.util.List;

/**
 * @author admin
 */
public interface CategoryService<Category> {
    /**
     * 返回菜单树
     *
     * @return 菜单树
     */
    List<Category> list();
}
