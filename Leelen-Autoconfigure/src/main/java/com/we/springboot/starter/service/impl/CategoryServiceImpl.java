package com.we.springboot.starter.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.we.springboot.starter.bean.BaseCategory;
import com.we.springboot.starter.mapper.BaseCategoryMapper;
import com.we.springboot.starter.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admin
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private BaseCategoryMapper categoryMapper;

    @Override
    public List<BaseCategory> list() {
        Wrapper<BaseCategory> wrapper = new EntityWrapper<BaseCategory>().orderDesc(Collections.singletonList("sequence"));
        //获取菜单
        List<BaseCategory> categoryList = categoryMapper.selectList(wrapper);
        //构造菜单层级结构
        return buildCategoryTree(categoryList);
    }


    /**
     * 构造菜单层级结构
     *
     * @param categoryList 菜单列表
     */
    private List<BaseCategory> buildCategoryTree(List<BaseCategory> categoryList) {
        List<BaseCategory> parent = new ArrayList<>();

        for (BaseCategory baseCategory : categoryList) {
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
    private List<BaseCategory> getChildren(String categorySign, List<BaseCategory> categoryList) {
        List<BaseCategory> list = new ArrayList<>();
        for (BaseCategory baseCategory : categoryList) {
            if (categorySign.equals(baseCategory.getCategoryParentSign())) {
                baseCategory.setChildren(getChildren(baseCategory.getCategorySign(), categoryList));
                list.add(baseCategory);
            }
        }
        Collections.sort(list);
        return list;

    }


}
