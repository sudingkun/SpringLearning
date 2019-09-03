package com.we.springboot.excel.bean;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Excel(name = "创建时间", width = 15)
    private String paymentDate;

    private String costs;

    @Excel(name = "总额", width = 10)
    private Double total;


}
