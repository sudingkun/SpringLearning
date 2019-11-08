package com.we.springboot.mybatis.sql;

import lombok.Data;
import lombok.ToString;

import java.util.Date;


/**
 * sql操作实体类
 * @author we
 */
@Data
@ToString
public class BaseSql {

    public String createBy;

    public String updateBy;

    public Date createTime;

    public Date updateTime;

    public BaseSql() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
