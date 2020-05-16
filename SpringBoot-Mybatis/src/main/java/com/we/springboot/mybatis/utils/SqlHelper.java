package com.we.springboot.mybatis.utils;

import com.we.springboot.mybatis.sql.BaseSql;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 *  fixme 这个写的有问题
 * 插入数据库的po参数太少，经常需要从请求中获取再set进去
 * <p>
 * 创建的dto名称和po相同，前端需要不同的名称使用@JsonProperty 结合
 * sql set工具
 *
 * @author we
 */
@Slf4j
@Data
@Component
public class SqlHelper {


    private SqlHelper() {
    }

    private static BaseSql baseSql = new BaseSql();

    private static SqlHelper sqlHelper = new SqlHelper();


    /**
     * 设置操作人员姓名
     * web请求可以使用
     *
     * @param o 插入数据库实体类
     */
    public void setOperatorName(Object o) {
        String name = "you can get operator name from any way";
        baseSql.setCreateBy(name);
        baseSql.setUpdateBy(name);
        processObject(o);
    }

    /**
     * 设置操作人员姓名
     *
     * @param o    插入数据库实体类
     * @param name 姓名
     */
    public static void setOperatorName(Object o, String name) {
        baseSql.setCreateBy(name);
        baseSql.setUpdateBy(name);
        processObject(o);
    }

    /**
     * getFields()：获取所有public字段,包括父类字段
     * getDeclaredFields()：获取所有字段,public和protected和private,但是不包括父类字段
     *
     * @param o 插入数据库实体类
     */
    private static void processObject(Object o) {
        Class<?> cls = baseSql.getClass();
        Class<?> aClass = o.getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        Field[] fields = aClass.getFields();

        HashMap<String, Object> map = new HashMap<>(8);
        try {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                String fieldName = declaredField.getName();
                Object fieldValue = declaredField.get(baseSql);
                map.put(fieldName, fieldValue);
            }

            for (Field field : fields) {
                field.setAccessible(true);
                if (map.containsKey(field.getName())) {
                    field.set(o, map.get(field.getName()));
                }
            }

        } catch (IllegalAccessException e) {
            log.error("process fail{}", e.getMessage());
            e.printStackTrace();
        }

    }
}
