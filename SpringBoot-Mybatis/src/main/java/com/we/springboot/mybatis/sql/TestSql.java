package com.we.springboot.mybatis.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * callSuper = true即解决缺少父类属性的问题
 *
 * @author we
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestSql extends BaseSql {

    public String name;

    public Integer age;
}
