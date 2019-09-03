package com.we.springboot.excel.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.handler.MapImportHandler;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

    @RequestMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Bill.class, getAll());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("账单.xls", "UTF-8"));
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/import")
    @ResponseBody
    public List<Bill> importUsers() throws Exception {
        File file = new File("C:/Users/admin/Desktop/账单.xls");
        ImportParams importParams = new ImportParams();
        MapImportHandler importHandler = new MapImportHandler();
        importParams.setDataHandler(importHandler);
        List<Map<String, Object>> objects = ExcelImportUtil.importExcel(file, Map.class, importParams);
        List<Bill> bills = new ArrayList<>();
        for (Map<String, Object> object : objects) {
            Bill bill = new Bill();
            bill.setName((String) object.get("用户名"));
            bill.setPhone((String) object.get("手机号"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String paymentDate = object.get("创建时间").toString();
            bill.setPaymentDate(format.parse(paymentDate));
            bill.setCosts((String) object.get("costs"));
            bill.setTotal(Double.parseDouble((String) object.get("总额")));
            bills.add(bill);
        }
        return bills;
    }

}
