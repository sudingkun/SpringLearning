package com.we.springboot.shiro.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.springboot.shiro.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author sudingkun
 * @create 2019/8/19 15:25
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findByUserName(@Param("username")String username);




}