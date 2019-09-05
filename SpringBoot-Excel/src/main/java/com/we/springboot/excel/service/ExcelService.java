package com.we.springboot.excel.service;


import com.alibaba.fastjson.JSONObject;
import com.we.springboot.excel.bean.Bill;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author sudingkun
 */
@Service
public class ExcelService {

    public List<Bill> getAll() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("张三", "110", new Date(), 100D));
        bills.add(new Bill("李四", "120", new Date(), 110D));
        bills.add(new Bill("王五", "119", new Date(), 120D));
        return bills;
    }

    public static List<Bill> getCustom() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("张三", "110", new Date(), "{\"水费\":50,\"电费\":50,\"物业费\":10}", 110D));
        bills.add(new Bill("李四", "120", new Date(), "{\"水费\":50,\"电费\":60,\"物业费\":20}", 130D));
        bills.add(new Bill("王五", "119", new Date(), "{\"水费\":60,\"电费\":20,\"物业费\":10}", 90D));
        return bills;
    }

    public  List<Map<String, String>> bean2Map(List<Bill> bills) throws Exception {
        List<Map<String, String>> data = new ArrayList<>();
        for (Bill bill : bills) {
            Map<String, String> result = BeanUtils.describe(bill);
            data.add(result);
        }
        return data;
    }


    public List<Map<String, Object>> getCustom(List<Map<String, String>> dataList) {
        for (Map<String, String> m : dataList) {
            Map<String, String> cost = JSONObject.parseObject(m.get("costs"), Map.class);
            for (Map.Entry<String, String> entry : cost.entrySet()) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", entry.getKey());
                hashMap.put("value", entry.getKey());
//                columns.add(hashMap);
            }
        }

        return null;
    }

/*    public static List<Bill> process(List<Bill> bills) {
        for (Bill custom : bills) {
            Object o = JSON.parseObject((byte[]) custom.getCosts(), Map.class);
            custom.setCosts(o);
        }
        return bills;
    }*/

}
