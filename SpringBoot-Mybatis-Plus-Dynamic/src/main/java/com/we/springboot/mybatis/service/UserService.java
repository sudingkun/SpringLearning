package com.we.springboot.mybatis.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.we.springboot.mybatis.bean.User;
import com.we.springboot.mybatis.dao.UserMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author: sudingkun
 * @date: 2019-08-08 11:11
 */
@Service
@DS("slave")
public class UserService extends ServiceImpl<UserMapper, User> {



    @Override
    @DS("master")
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    @DS("master")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }


    @Override
    public List<User> list(Wrapper<User> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    @DS("slave")
    public boolean update(User entity, Wrapper<User> updateWrapper) {
        return super.update(entity, updateWrapper);
    }
}
