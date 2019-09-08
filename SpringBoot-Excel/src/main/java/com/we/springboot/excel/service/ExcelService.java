package com.we.springboot.excel.service;


import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.constants.Constants;
import com.we.springboot.excel.dao.BillMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author sudingkun
 */
@Service
@RequiredArgsConstructor
public class ExcelService {

    private final BillMapper billMapper;

    public List<Bill> getBillList() {
        return billMapper.selectList(null);
    }


    public Map<String, Object> getCustomBills() {
        return process(billMapper.selectList(null));
    }

    /**
     * 把原始数据处理成可以被模板解析
     *
     * @param bills 账单原始数据
     */
    private Map<String, Object> process(List<Bill> bills) {
        Set<Map<String, Object>> costList = new HashSet<>();

        for (Bill bill : bills) {
            Map<String, Object> costMap = bill.getCosts();
            for (String key : costMap.keySet()) {
                if (!Constants.Bill.TOTAL.equals(key)) {
                    Map<String, Object> cost = new HashMap<>(2);
                    cost.put("name", key);
                    cost.put("value", "t.costs." + key);
                    costList.add(cost);
                }
            }
        }
        //41行if判断和这里加上这个是为了保证，"总额"读取时能排在最后
        //水费、总额
        //水费、电费、总额
        //如果不做处理，最后会变成 水费、总额、电费
        Map<String, Object> cost = new HashMap<>(2);
        cost.put("name", Constants.Bill.TOTAL);
        cost.put("value", "t.costs." + Constants.Bill.TOTAL);
        costList.add(cost);

        Map<String, Object> map = new HashMap<>(2);
        map.put("colList", costList);
        map.put("bills", bills);
        return map;
    }


}
