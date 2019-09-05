package com.we.springboot.excel.bean;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author sudingkun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Excel(name = "用户名", width = 10)
    private String name;

    @Excel(name = "手机号", width = 15)
    private String phone;

    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 20)
    private Date paymentDate;

    @ExcelIgnore
    private Object costs;

    @Excel(name = "总额", width = 10)
    private Double total;

    public Bill(String name, String phone, Date paymentDate, Double total) {
        this.name = name;
        this.phone = phone;
        this.paymentDate = paymentDate;
        this.total = total;
    }
}
