package com.we.springboot.starter.service.impl;


import com.we.springboot.starter.bean.BaseCategory;
import com.we.springboot.starter.mapper.BaseCategoryRepository;
import com.we.springboot.starter.service.CategoryService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admin
 */
public class CategoryServiceImpl<Category extends BaseCategory> implements CategoryService<Category> {

    @Resource
    private BaseCategoryRepository<Category> categoryMapper;

    @Override
    public List<Category> list() {
        //获取菜单
        List<Category> categoryList = categoryMapper.findAll();
        //构造菜单层级结构
        return buildCategoryTree(categoryList);
    }


    /**
     * 构造菜单层级结构
     *
     * @param categoryList 菜单列表
     */
    private List<Category> buildCategoryTree(List<Category> categoryList) {
        List<Category> parent = new ArrayList<>();

        for (Category baseCategory : categoryList) {
            String categorySign = baseCategory.getCategorySign();
            String categoryParentSign = baseCategory.getCategoryParentSign();
            if (StringUtils.isEmpty(categoryParentSign)) {
                baseCategory.setChildren(getChildren(categorySign, categoryList));
                parent.add(baseCategory);
            }
        }
        Collections.sort(parent);
        return parent;
    }

    /**
     * 获取子列表
     *
     * @param categorySign 父级菜单标识
     * @param categoryList 菜单列表
     * @return 子菜单列表
     */
    private List<Category> getChildren(String categorySign, List<Category> categoryList) {
        List<Category> list = new ArrayList<>();
        for (Category baseCategory : categoryList) {
            if (categorySign.equals(baseCategory.getCategoryParentSign())) {
                baseCategory.setChildren(getChildren(baseCategory.getCategorySign(), categoryList));
                list.add(baseCategory);
            }
        }
        Collections.sort(list);
        return list;
    }


}
