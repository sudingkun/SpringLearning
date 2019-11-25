package com.we.springboot.starter.service;


import java.util.List;

/**
 * @author we
 */
public interface CategoryService<Category> {
    /**
     * 返回菜单树
     *
     * @return 菜单树
     */
    List<Category> list();
}
