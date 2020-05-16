package com.we.springboot.mybatis.interceptor;


import com.we.springboot.mybatis.annotation.TableNameIntercept;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


/**
 * 自定义拦截器：分表的时候用来替换表名
 * 现在编写的拦截器在使用pageHelper分页时，会失效（会先去查找数量再去执行对应的sql）。目前用在mapper类上加上@TableNameIntercept这个暂时解决
 * 这个写的不好，使用下面的动态表名解析器
 * (https://gitee.com/baomidou/mybatis-plus-samples/tree/master/mybatis-plus-sample-dynamic-tablename)
 *
 * @author we
 */
@Deprecated
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class TableNameInterceptor implements Interceptor {

    private String[] tableNames;
    private Boolean isProcessed;


    /**
     * 根据实际情况自定义替换tableName
     *
     * @param tableName 替换的表名
     * @return
     */
    private String setTableName(String tableName) {
        tableName += "_afterReplace";
        return tableName;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        isProcessed = Boolean.FALSE;
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //获取数据源
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        BoundSql boundSql = statementHandler.getBoundSql();
        //获取到原始sql语句
        String sql = boundSql.getSql();

        String namespace = mappedStatement.getId();
        String className = namespace.substring(0, namespace.lastIndexOf('.'));
        String methodName = namespace.substring(namespace.lastIndexOf('.') + 1);


        Class<?> aClass = Class.forName(className);

        sql = methodProcess(sql, aClass, methodName);
        //如果方法上有注解，则忽略类上的注解
        if (aClass.isAnnotationPresent(TableNameIntercept.class) && !isProcessed) {
            TableNameIntercept annotation = aClass.getAnnotation(TableNameIntercept.class);
            tableNames = annotation.tableName();
            List<String> ignoreMethods = Arrays.asList(annotation.ignoreMethod());
            if (StringUtils.isEmpty(ignoreMethods)) {
                for (String tableName : tableNames) {
                    sql = sql.replace(tableName, setTableName(tableName));
                }
            } else {
                for (String tableName : tableNames) {
                    sql = sql.replace(tableName, setTableName(tableName));
                }
            }
        }


        //通过反射修改sql语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private String methodProcess(String sql, Class<?> aClass, String methodName) {
        //返回类的所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法。
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(TableNameIntercept.class) && methodName.equals(method.getName())) {
                //获取方法的注解
                TableNameIntercept interceptorAnnotation = method.getAnnotation(TableNameIntercept.class);
                tableNames = interceptorAnnotation.tableName();
                if (!StringUtils.isEmpty(tableNames)) {
                    for (String tableName : tableNames) {
                        sql = sql.replace(tableName, setTableName(tableName));
                        isProcessed = Boolean.TRUE;
                    }
                }
            }
        }
        return sql;
    }

}
