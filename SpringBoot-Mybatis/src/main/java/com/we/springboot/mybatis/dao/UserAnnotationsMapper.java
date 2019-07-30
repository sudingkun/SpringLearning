package com.we.springboot.mybatis.dao;

import com.github.pagehelper.util.StringUtil;
import com.we.springboot.mybatis.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @author sudingkun
 * 使用注解方式完成crud
 */
@Mapper
public interface UserAnnotationsMapper {

    /**
     * 新增用户
     *
     * @param user 插入对象
     * @return 返回成功条数(1代表成功 ， 0代表不成功)
     */
    @Insert("insert into user( name, age) values (#{name} ,#{age} )")
    int insert(User user);

    /**
     * 批量新增用户
     *
     * @param list 插入对象数组
     * @return 返回成功条数
     */
    @Insert({"<script>",
            "insert into user(name, age) values  ",
            "<foreach collection='list' item='item' index='index' separator=',' >",
            "(#{item.name}, #{item.age})",
            "</foreach></script>"})
    int batchInsert(@Param("list") List<User> list);

    /**
     * 根据id查找对应的用户
     *
     * @param id 用户id
     * @return 用户对象
     */
    @Select("select * from user where id=#{id}")
    User getById(@Param("id") Integer id);

    /**
     * 更新用户(根据主键)
     *
     * @param user 更新用户
     * @return 成功条数(1代表成功 ， 0代表不成功)
     */
    @Update("update user set name=#{name} ,age=#{age} where id=#{id} ")
    int update(User user);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 成功条数(1代表成功 ， 0代表不成功)
     */
    @Delete("delete from user where id=#{id}")
    int delete(@Param("id") Integer id);

    /**
     * 根据条件查询用户列表
     *
     * @param user 查询条件
     * @return 用户集合
     */
    @SelectProvider(type = UserProvider.class, method = "getByCondition")
    List<User> getByCondition(User user);

    /**
     * 查询所以用户
     *
     * @return 用户集合
     */
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "age", column = "age", javaType = Integer.class)
    })
    List<User> getAll();

    /**
     * 根据姓名，最小最大年龄查询用户
     *
     * @param name   姓名
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return 匹配的用户集合
     */
    @Select("select * from  user where name like concat('%',#{name} ,'%') and (age between #{minAge} and #{maxAge}) ")
    @ResultType(User.class)
    List<User> getByNameAndAgeBetween(@Param("name") String name, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);

    class UserProvider {
        public String getByCondition(User user) {
            StringBuilder sql = new StringBuilder("select * from user where 1=1 ");
            if (StringUtil.isNotEmpty(user.getName())) {
                sql.append(String.format("and name like '%s'", "%" + user.getName() + "%"));
            }
            if (!StringUtils.isEmpty(user.getAge())) {
                sql.append("and age = ").append(user.getAge());
            }
            return sql.toString();
        }

    }

}