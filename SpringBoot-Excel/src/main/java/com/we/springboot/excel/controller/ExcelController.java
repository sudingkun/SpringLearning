package com.we.springboot.excel.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.handler.MapImportHandler;
import com.we.springboot.excel.utils.ExcelUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sudingkun
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    private List<Bill> getAll() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("张三", "110", new Date(), null, 100D));
        bills.add(new Bill("李四", "120", new Date(), null, 110D));
        bills.add(new Bill("王五", "119", new Date(), null, 120D));
        return bills;
    }

    @RequestMapping("exportTemplate")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        Resource resource = new ClassPathResource("excelTemplate/excelTemplate.xlsx");
        response.setHeader("Content-Disposition", "attachment;filename=" + "excelTemplate.xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(new FileInputStream(resource.getFile()), outputStream);
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response) {
        //设置导出参数
        ExportParams params = new ExportParams();
        params.setSheetName("账单");
        params.setTitle("立林小区八月份账单");
        params.setType(ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, getAll());
        ExcelUtils.downLoadExcel("立林小区8月份账单.xlsx", response, workbook);
    }

    @RequestMapping("import")
    @ResponseBody
    public List<Bill> importExcel() {
        File file = new File("C:/Users/admin/Desktop/立林小区8月份账单.xlsx");
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setDataHandler(new MapImportHandler());
        return ExcelImportUtil.importExcel(file, Map.class, importParams);
    }

}
