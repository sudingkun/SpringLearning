package com.we.springboot.starter.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.we.springboot.starter.bean.BaseCategory;
import com.we.springboot.starter.mapper.BaseCategoryMapper;
import com.we.springboot.starter.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admin
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final BaseCategoryMapper categoryMapper;

    @Value("${leelencloud.system}")
    private String system;

    @Override
    public List<BaseCategory> list() {
        Wrapper<BaseCategory> wrapper = new EntityWrapper<BaseCategory>().orderDesc(Collections.singletonList("sequence"));//.ne("category_sign", system);
        //获取菜单
        List<BaseCategory> categoryList = categoryMapper.selectList(wrapper);
        //构造菜单层级结构
        buildCategoryTree(categoryList);
        return categoryList;
    }


    /**
     * 构造菜单层级结构
     *
     * @param categoryList 菜单列表
     */
    private void buildCategoryTree(List<BaseCategory> categoryList) {
       /* Iterator<BaseCategory> iterator = categoryList.iterator();
        while (iterator.hasNext()) {
            BaseCategory category = iterator.next();
            String categorySign = category.getCategorySign();
            String categoryParentSign = category.getCategoryParentSign();
            //如果是父节点
            if (system.equals(categoryParentSign)) {
                //遍历子节点
                for (BaseCategory children : categoryList) {
                    //是父节点下的子节点添加进去
                    if (categorySign.equals(children.getCategoryParentSign())) {
                        category.getChildren().add(children);
                    }
                }
            }
        }
        iterator = categoryList.iterator();
        while (iterator.hasNext()) {
            BaseCategory categoryDTO = iterator.next();
            String categoryParentSign = categoryDTO.getCategoryParentSign();
            //如果是父节点
            if (!system.equals(categoryParentSign)) {
                iterator.remove();
            }
        }*/

       /* HashMap<String, List<BaseCategory>> categoryMap = new HashMap<>(16);

        for (BaseCategory category : categoryList) {
            String categoryParentSign = category.getCategoryParentSign();
            if (StringUtils.isEmpty(categoryParentSign)) {
                continue;
            }
            List<BaseCategory> value = categoryMap.get(categoryParentSign);
            value = CollectionUtils.isEmpty(value) ? new ArrayList<>() : value;
            value.add(category);
            categoryMap.put(categoryParentSign, value);
        }

        List<BaseCategory> baseCategories = categoryMap.get(system);
        categoryMap.remove(system);



        for (String key : categoryMap.keySet()) {
            for (BaseCategory baseCategory : baseCategories) {
                if (baseCategory.getCategorySign().equals(key)) {
                    baseCategory.setChildren(categoryMap.get(key));
                }
            }
        }

        System.out.println(baseCategories);*/


        List<BaseCategory> parent = new ArrayList();

        for (BaseCategory baseCategory : categoryList) {
            String categorySign = baseCategory.getCategorySign();
            if (system.equals(categorySign)) {
                baseCategory.setChildren(getChildren(categorySign, categoryList));
                parent.add(baseCategory);
            }
        }
        System.out.println(parent);

    }

    private List getChildren(String categorySign, List<BaseCategory> categoryList) {
        List<BaseCategory> list = new ArrayList<>();
        for (BaseCategory baseCategory : categoryList) {
            if (categorySign.equals(baseCategory.getCategoryParentSign())) {
                baseCategory.setChildren(getChildren(baseCategory.getCategoryParentSign(), list));
                list.add(baseCategory);
            }
        }
        return list;

    }


}
