package com.we.springboot.mybatis.dao;

import com.we.springboot.mybatis.annotation.TableNameIntercept;
import com.we.springboot.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sudingkun
 * 使用xml方式完成crud
 */
@Mapper
@TableNameIntercept(tableName = "user")
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 插入对象
     * @return 返回是否成功(1代表成功 ， 0代表不成功)
     */
    int insert(User user);

    /**
     * 批量新增用户
     *
     * @param list 插入对象数组
     * @return 返回成功条数
     */
    int batchInsert(@Param("list") List<User> list);

    /**
     * 根据id查找对应的用户
     *
     * @param id 用户id
     * @return 用户对象
     */
    User getById(@Param("id") Integer id);

    /**
     * 更新用户(根据主键)
     *
     * @param user 更新用户
     * @return 成功条数(1代表成功 ， 0代表不成功)
     */
    int update(User user);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 成功条数(1代表成功 ， 0代表不成功)
     */
    int delete(@Param("id") Integer id);

    /**
     * 根据条件查询用户列表
     *
     * @param user 查询条件
     * @return 用户集合
     */
    @TableNameIntercept(tableName = "user")
    List<User> getByCondition(User user);

    /**
     * 查询所以用户
     *
     * @return 用户集合
     */
    List<User> getAll();

    /**
     * 根据姓名，最小最大年龄查询用户
     *
     * @param name   姓名
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return 匹配的用户集合
     */
    List<User> getByNameAndAgeBetween(@Param("name") String name, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);


}