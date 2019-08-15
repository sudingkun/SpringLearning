package com.we.datasource.dynamic.config;

import com.baomidou.dynamic.datasource.DynamicDataSourceCreator;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.we.datasource.dynamic.bean.DynamicDataSourceProperty;
import com.we.datasource.dynamic.mapper.DBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * spring加载完成后会调用这个方法加载数据源
 * @author: sudingkun
 * @date: 2019-08-15 11:31
 */
@Component
@RequiredArgsConstructor
public class DynamicDataSource implements ApplicationListener<ApplicationReadyEvent> {

    private final DBMapper dbMapper;

    private final DynamicRoutingDataSource dynamicRoutingDataSource;

    private final DynamicDataSourceCreator dynamicDataSourceCreator;


    /**
     * @param applicationReadyEvent 可以用来获取已经加载好的bean(上面注入的都可以获取)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //从数据库获取数据源
        List<DynamicDataSourceProperty> dataSources = this.dbMapper.getAll();

        for (DynamicDataSourceProperty dataSource : dataSources) {
            //创建一个数据源属性
            DataSourceProperty dataSourceProperty = new DataSourceProperty();
            //设置连接属性
            dataSourceProperty.setPollName(dataSource.getName());
            dataSourceProperty.setUsername(dataSource.getUsername());
            dataSourceProperty.setPassword(dataSource.getPassword());
            dataSourceProperty.setUrl(dataSource.getUrl());
            dataSourceProperty.setDriverClassName(dataSource.getDriverClassName());
            //创建一个数据源（默认有全局的druid配置）
            DataSource druidDataSource = dynamicDataSourceCreator.createDruidDataSource(dataSourceProperty);
            dynamicRoutingDataSource.addDataSource(dataSource.getName(), druidDataSource);
        }

    }
}
