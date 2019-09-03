package com.we.springboot.excel.handler;


import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sudingkun
 */
public class MapImportHandler extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

    private static final String TOTAL = "总额";

    private static Boolean TAG = Boolean.TRUE;

    private static final String COLUMN = "costs";

    private HashMap<String, Object> hashMap = new HashMap<>();


    @Override
    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        if (TAG) {
            map.put(originKey, value);
            if (TOTAL.equals(originKey)) {
                TAG = Boolean.FALSE;
            }
        } else {
            hashMap.put(originKey, value);
            String jsonString = JSON.toJSONString(hashMap);
            map.put(COLUMN, jsonString);
        }
    }


}
