package com.we.springboot.excel.service;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.dao.BillDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author sudingkun
 */
@Service
@RequiredArgsConstructor
public class ExcelService {

    private final BillDao billDao;

    public List<Bill> getBills() {
        return billDao.getAll();
    }

    public Map<String, Object> getCustomBills() {
        return process(billDao.getCustom());
    }

    /**
     * 把原始数据处理成可以被模板解析
     *
     * @param bills 账单原始数据
     */
    private Map<String, Object> process(List<Bill> bills) {
        Set<Map<String, Object>> costList = new HashSet<>();
        List<Map<String, Object>> billList = new ArrayList<>();

        for (Bill bill : bills) {
            Map<String, Object> billMap = BeanUtil.beanToMap(bill);
            Map<String, Object> costMap = JSONObject.parseObject(bill.getCosts());

            for (Map.Entry<String, Object> entry : costMap.entrySet()) {
                String key = entry.getKey();
                double value = Double.parseDouble(entry.getValue().toString());
                HashMap<String, Object> cost = new HashMap<>(8);
                cost.put("name", key);
                cost.put("value", "t." + key);
                costList.add(cost);
                billMap.put(key, value);
            }
            billList.add(billMap);
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("colList", costList);
        map.put("bills", billList);
        return map;
    }


}
