package com.we.springboot.excel.bean;

import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Excel导入校验类
 * @author welov
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExcelVerifyEntityOfMode extends Bill implements IExcelModel, IExcelDataModel {

    private int rowNum;

    private String errorMsg;


}
