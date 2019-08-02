package com.we.springboot.mybatis.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author sudingkun
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入自动填充");
        setInsertFieldValByName("age", 18, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
