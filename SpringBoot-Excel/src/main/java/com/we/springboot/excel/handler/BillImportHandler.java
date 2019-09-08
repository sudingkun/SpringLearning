package com.we.springboot.excel.handler;


import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.alibaba.fastjson.JSONObject;
import com.we.springboot.excel.constants.Constants;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sudingkun
 */
@NoArgsConstructor
public class BillImportHandler extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

    private String startColumn = Constants.Bill.PAYMENT_DATE;

    private String endColumn = Constants.Bill.TOTAL;

    public BillImportHandler(String startColumn, String endColumn) {
        this.startColumn = startColumn;
        this.endColumn = endColumn;
    }

    private static Boolean TAG = Boolean.FALSE;

    private JSONObject costsValue = new JSONObject(new LinkedHashMap<>());

    @Override
    public void setMapValue(Map<String, Object> map, String originKey, Object value) {

        if (TAG) {
            if (endColumn.equals(originKey)) {
                TAG = Boolean.FALSE;
                map.put("total", value);
            }
            costsValue.put(originKey, value);
            originKey = Constants.CUSTOM_COLUMN;
            value = costsValue;
        }
        if (startColumn.equals(originKey)) {
            TAG = Boolean.TRUE;
            costsValue = new JSONObject(new LinkedHashMap<>());
        }

        //把excel对应的名称转换成对象对应的字段
        switch (originKey) {
            case Constants.Bill.NAME:
                originKey = "name";
                break;
            case Constants.Bill.PHONE:
                originKey = "phone";
                break;
            case Constants.Bill.PAYMENT_DATE:
                originKey = "paymentDate";
                break;
            case Constants.Bill.TOTAL:
                originKey = "total";
                break;
            default:
                originKey = Constants.CUSTOM_COLUMN;
        }

        map.put(originKey, value);
    }


}
