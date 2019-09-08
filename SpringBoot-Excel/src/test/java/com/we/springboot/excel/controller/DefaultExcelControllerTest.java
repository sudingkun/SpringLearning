package com.we.springboot.excel.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.bean.ExcelVerifyEntityOfMode;
import com.we.springboot.excel.service.ExcelService;
import com.we.springboot.excel.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultExcelControllerTest {

    @Autowired
    private ExcelService excelService;

    @Test
    public void exportData() throws IOException {
        ExportParams params = new ExportParams();
        params.setType(ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, excelService.getBillList());
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/welov/Desktop/bill.xlsx");
        ExcelUtils.downLoadExcel(fileOutputStream, workbook);
    }

    @Test
    public void importExcel() {
        File file = new File("C:/Users/welov/Desktop/bill.xlsx");
        ImportParams importParams = new ImportParams();
        importParams.setNeedVerify(true);
//        importParams.setVerifyHandler(new ExcelVerifyHandlerImpl());


        ExcelImportResult<ExcelVerifyEntityOfMode> result = ExcelImportUtil.importExcelMore(file, ExcelVerifyEntityOfMode.class, importParams);
        System.out.println(result);
    }

}