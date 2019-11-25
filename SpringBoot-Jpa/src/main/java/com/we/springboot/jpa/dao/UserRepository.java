package com.we.springboot.jpa.dao;


import com.we.springboot.jpa.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author we
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据条件查询用户集合
     *
     * @param name name
     * @param min  minAge
     * @param max  maxAge
     * @return user集合
     */
    List<User> findByNameLikeAndAgeBetween(String name, Integer min, Integer max);
}
