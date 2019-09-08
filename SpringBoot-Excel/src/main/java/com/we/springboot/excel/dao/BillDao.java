package com.we.springboot.excel.dao;


import com.alibaba.fastjson.JSONObject;
import com.we.springboot.excel.bean.Bill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author sudingkun
 */
@Repository
public class BillDao {


    public List<Bill> getCustom() {
        List<Bill> bills = new ArrayList<>();
        JSONObject costs = new JSONObject(new LinkedHashMap<>());
        costs.put("水费", 50.00);
        costs.put("电费", 50.00);
        costs.put("总额", 100.00);
        bills.add(new Bill("张三", "133", new Date(), costs, 60d));
        costs = new JSONObject(new LinkedHashMap<>());
        costs.put("水费", 60.50);
        costs.put("物业费", 50.50);
        costs.put("总额", 111.00);
        bills.add(new Bill("李四", "144", new Date(), costs, 60d));
        costs = new JSONObject(new LinkedHashMap<>());
        costs.put("电费", 20.00);
        costs.put("物业费", 20.00);
        costs.put("总额", 40);
        bills.add(new Bill("王五", "155", new Date(), costs, 70d));
        costs = new JSONObject(new LinkedHashMap<>());
        costs.put("水费", 10);
        costs.put("电费", 20);
        costs.put("物业费", 30);
        costs.put("总额", 60);
        bills.add(new Bill("赵6", "166", new Date(), costs, 0.5));
        return bills;
    }
}
