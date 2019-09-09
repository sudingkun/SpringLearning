package com.we.springboot.excel.handler;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;



/**
 * 用来处理对象的（map不行）
 * 使用bean注解导入导出的时候，可以对一些数据进行翻译
 * @author welov
 */
public class ExcelDictHandlerImpl implements IExcelDictHandler {

    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        System.out.println(dict);
        return null;
    }

    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        System.out.println(dict);
        return null;
    }
}
