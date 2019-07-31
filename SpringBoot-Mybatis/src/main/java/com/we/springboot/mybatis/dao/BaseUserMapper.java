package com.we.springboot.mybatis.dao;

import com.we.springboot.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author sudingkun
 * @create 2019/7/31 14:40
 */
@Mapper
public interface BaseUserMapper extends tk.mybatis.mapper.common.Mapper<User>, MySqlMapper<User> {

}
