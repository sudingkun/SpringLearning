package com.we.springboot.mybatis.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.springboot.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author sudingkun
 * @create 2019/7/31 14:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
