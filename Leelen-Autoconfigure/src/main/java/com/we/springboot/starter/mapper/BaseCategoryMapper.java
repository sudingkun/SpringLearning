package com.we.springboot.starter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.springboot.starter.annotation.TableNameIntercept;
import com.we.springboot.starter.bean.BaseCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 */
@Mapper
@TableNameIntercept(tableName = "base_category")
public interface BaseCategoryMapper<Category extends BaseCategory> extends BaseMapper<Category> {
}
