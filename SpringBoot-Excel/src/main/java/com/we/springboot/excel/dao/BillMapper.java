package com.we.springboot.excel.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.springboot.excel.bean.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author welov
 */
@Mapper
public interface BillMapper extends BaseMapper<Bill> {

    /**
     * 批量插入账单
     *
     * @param list 账单
     * @return 插入结果
     */
    int insertList(@Param("list") List<Bill> list);


    /**
     * 获取所有账单
     * @return
     */
    List<Bill> getAll();



}
