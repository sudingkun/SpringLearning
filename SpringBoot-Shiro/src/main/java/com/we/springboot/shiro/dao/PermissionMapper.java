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

    /**
     *  通过用户名查询用户对应的权限列表
     * @param username 用户名
     * @return 用户权限列表
     */
    Set<Permission> findByUserName(@Param("username") String username);

}