package com.we.springboot.excel.handler;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;



/**
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
