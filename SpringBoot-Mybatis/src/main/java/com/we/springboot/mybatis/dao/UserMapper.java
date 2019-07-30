package com.we.springboot.mybatis.dao;

import com.we.springboot.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @Author sudingkun
 * @create 2019/7/30 19:16
 */
@Mapper
public interface UserMapper {

    int insert(User user);

    int delete(@Param("id") Integer id);

    User getById(@Param("id")Integer id);

    List<User> getByCondition(User user);

    List<User> getAll();

    int update(User user);
}