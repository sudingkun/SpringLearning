package com.we.springboot.excelboot.bean;//package com.we.sdk.excel.bean;

import com.excel.poi.annotation.ExportField;
import com.excel.poi.annotation.ImportField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 导出导入实体对象
 * <p>
 * 导出注解说明
 * columnName:导出Excel列名
 * scale:导出BigDecimal类型格式化精度
 * roundingMode:导出BigDecimal类型舍入规则
 * dateFormat:导出Data类型格式化模式
 * defaultCellValue:导出模板默认值
 * <p>
 * 导入注解说明
 * required:是否非空校验
 * regex:正则校验规则
 * regexMessage:正则校验失败信息
 * scale:导出BigDecimal类型格式化精度
 * roundingMode:导出BigDecimal类型舍入规则
 * dateFormat:导出Data类型格式化模式
 *
 * @author sudingkun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {

    @ExportField(columnName = "用户名", defaultCellValue = "张三")
    @ImportField
    private String name;

    @ExportField(columnName = "手机号")
    @ImportField(regex = "^1([38]\\d|4[5-9]|5[0-35-9]|6[56]|7[0-8]|9[189])\\d{8}$", regexMessage = "手机号输入有误")
    private String phone;


    @ExportField(columnName = "创建时间", dateFormat = "yyyy-MM-dd", defaultCellValue = "2019-09-10")
    @ImportField(dateFormat = "yyyy-MM-dd")
    private Date paymentDate;

    @ExportField(columnName = "总额", defaultCellValue = "100", scale = 2)
    @ImportField
    private BigDecimal total;

    private String costs;

}
