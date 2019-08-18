package com.we.springboot.shiro.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.springboot.shiro.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsername(@Param("username")String username);


}