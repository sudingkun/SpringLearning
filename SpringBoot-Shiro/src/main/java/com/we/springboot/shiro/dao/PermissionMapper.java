package com.we.springboot.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.springboot.shiro.bean.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Author sudingkun
 * @create 2019/8/22 11:22
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    Set<Permission> findByUserName(@Param("username") String username);

}