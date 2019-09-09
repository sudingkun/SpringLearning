package com.we.springboot.excel.bean;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author sudingkun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("excel_bill")
public class Bill {
    @Excel(name = "用户名", width = 10)
    @NotBlank
    private String name;

    @Excel(name = "手机号", width = 15)
    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "手机号格式不对")
    private String phone;

    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 20)
    private String paymentDate;

    @ExcelIgnore
    private JSONObject costs;

    @Excel(name = "总额", width = 10)
    private Double total;

}
