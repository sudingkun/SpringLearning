package com.we.springboot.excel.dao;


import com.we.springboot.excel.bean.Bill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sudingkun
 */
@Repository
public class BillDao {

    public List<Bill> getAll() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("张三", "110", new Date(), 100D));
        bills.add(new Bill("李四", "120", new Date(), 110D));
        bills.add(new Bill("王五", "119", new Date(), 120D));
        return bills;
    }

    public List<Bill> getCustom() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("张三", "133", new Date(), "{\"水费\":\"20.0\",\"物业费\":\"10.0\",\"电费\":\"30.0\",\"总额\":\"60.0\"}", 60d));
        bills.add(new Bill("李四", "144", new Date(), "{\"水费\":\"40.0\",\"电费\":\"20.0\",\"总额\":\"60.0\"}", 60d));
        bills.add(new Bill("王五", "155", new Date(), "{\"水费\":\"50.0\",\"物业费\":\"20.0\",\"总额\":\"70.0\"}", 70d));
        bills.add(new Bill("赵6", "166", new Date(), "{\"水费\":\"0.0\",\"物业费\":\"0.5\",\"总额\":\"0.5\"}", 0.5));
        return bills;
    }
}
