package com.we.springboot.excel.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.handler.MapImportHandler;
import com.we.springboot.excel.service.ExcelService;
import com.we.springboot.excel.utils.ExcelUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sudingkun
 */
@Controller
@RequestMapping("customExcel")
@RequiredArgsConstructor
public class CustomExcelController {

    private final ExcelService excelService;

    @RequestMapping("exportTemplate")
    public void exportTemplate(HttpServletResponse response) throws Exception {
      /*  TemplateExportParams params = new TemplateExportParams("excelTemplate/template.xlsx");
        params.setColForEach(true);
        List<Map<String, String>> colList = new ArrayList<>();
        Map<String, String> map = new HashMap<>(2);
        map.put("name", "水费");
        map.put("value", "w.water");
        colList.add(map);
        map.put("name", "电费");
        map.put("value", "e.electricity");
        colList.add(map);

        List<Map<String, String>> maps = new ArrayList<>();
        List<Bill> bills = excelService.getCustom();
        for (Bill bill : bills) {
            Map<String, String> describe = BeanUtils.describe(bill);
            maps.add(describe);
        }
        System.out.println(map);*/

       /* List<Map<String, Object>> columns = new ArrayList<>();
        for (Map<String, Object> m : dataList) {
            Map<String, Object> cost = (Map<String, Object>) m.get("costs");
            for (Map.Entry<String, Object> entry : cost.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("cost", entry.getValue());
                hashMap.put("cost_value", entry.getValue());
                columns.add(hashMap);
            }
        }*/


//        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
//        ExcelUtils.downLoadExcel("template2.xlsx", response, workbook);
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, FileNotFoundException {
        TemplateExportParams params = new TemplateExportParams("excelTemplate/billTemplate.xlsx");
        params.setColForEach(true);
        List<Map<String, String>> colList = new ArrayList<>();
        Map<String, String> map = new HashMap<>(2);
        map.put("name", "水费");
        map.put("value", "w.water");
        colList.add(map);
        map.put("name", "电费");
        map.put("value", "e.electricity");
        colList.add(map);

        List<Map<String, String>> maps = new ArrayList<>();
        List<Bill> bills = ExcelService.getCustom();
        for (Bill bill : bills) {
            Map<String, String> describe = BeanUtils.describe(bill);
            describe.put("water", "50");
            describe.put("electricity", "50");
            maps.add(describe);
        }
        Map<String, Object> value = new HashMap<>(2);
        value.put("bills", map);
        value.put("colList", colList);
        Workbook workbook = ExcelExportUtil.exportExcel(params, value);
        ExcelUtils.downLoadExcel(new FileOutputStream("C:/Users/welov/Desktop/bill.xlsx"), workbook);
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
