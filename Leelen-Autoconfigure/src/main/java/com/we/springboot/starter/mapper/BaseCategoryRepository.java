package com.we.springboot.starter.mapper;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author admin
 */
@NoRepositoryBean
public interface BaseCategoryRepository<Category> extends JpaRepository<Category, Long> {
}
