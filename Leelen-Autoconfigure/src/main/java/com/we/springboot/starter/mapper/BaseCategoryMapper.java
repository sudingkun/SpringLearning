package com.we.springboot.starter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.springboot.starter.bean.BaseCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 */
@Mapper
public interface BaseCategoryMapper extends BaseMapper<BaseCategory> {
}
