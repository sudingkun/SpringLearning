package com.we.datasource.dynamic.mapper;

import com.we.datasource.dynamic.bean.DynamicDataSourceProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author admin
 * @create 2019/8/15 12:14
 */
@Mapper
public interface DBMapper  {

    @Select("select * from db")
    List<DynamicDataSourceProperty> getAll();
}
