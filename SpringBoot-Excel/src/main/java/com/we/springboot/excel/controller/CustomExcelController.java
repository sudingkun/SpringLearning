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
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

   /* @RequestMapping("exportTemplate")
    public void exportTemplate(HttpServletResponse response) throws Exception {
        TemplateExportParams params = new TemplateExportParams("excelTemplate/template.xlsx");
        params.setColForEach(true);
        List<Bill> bills = excelService.getCustom();
        List<Map<String, Object>> dataList = excelService.bean2Map(bills);

        Map<String, Object> map = new HashMap<>(2);
        map.put("dataList", dataList);

        List<Map<String, Object>> columns = new ArrayList<>();
        for (Map<String, Object> m : dataList) {
            Map<String, Object> cost = (Map<String, Object>) m.get("costs");
            for (Map.Entry<String, Object> entry : cost.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("cost", entry.getValue());
                hashMap.put("cost_value", entry.getValue());
                columns.add(hashMap);
            }
        }


        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        ExcelUtils.downLoadExcel("template2.xlsx", response, workbook);
    }*/

    public static void main(String[] args) throws IOException {
        Map<String, Object> value = new HashMap<String, Object>();
        List<Map<String, Object>> colList = new ArrayList<Map<String, Object>>();
        //先处理表头
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "小明挑战");
        map.put("zq", "正确");
        map.put("cw", "错误");
        map.put("tj", "统计");
        map.put("zqmk", "t.zq_xm");
        map.put("cwmk", "t.cw_xm");
        map.put("tjmk", "t.tj_xm");
        colList.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "小红挑战");
        map.put("zq", "正确");
        map.put("cw", "错误");
        map.put("tj", "统计");
        map.put("zqmk", "n:t.zq_xh");
        map.put("cwmk", "n:t.cw_xh");
        map.put("tjmk", "n:t.tj_xh");
        colList.add(map);

        value.put("colList", colList);

        List<Map<String, Object>> valList = new ArrayList<Map<String, Object>>();
        map = new HashMap<String, Object>();
        map.put("one", "运动");
        map.put("two", "跑步");
        map.put("zq_xm", 1);
        map.put("cw_xm", 2);
        map.put("tj_xm", 3);
        map.put("zq_xh", 4);
        map.put("cw_xh", 2);
        map.put("tj_xh", 6);
        valList.add(map);
        map = new HashMap<String, Object>();
        map.put("one", "运动");
        map.put("two", "跳高");
        map.put("zq_xm", 1);
        map.put("cw_xm", 2);
        map.put("tj_xm", 3);
        map.put("zq_xh", 4);
        map.put("cw_xh", 2);
        map.put("tj_xh", 6);
        valList.add(map);
        map = new HashMap<String, Object>();
        map.put("one", "文化");
        map.put("two", "数学");
        map.put("zq_xm", 1);
        map.put("cw_xm", 2);
        map.put("tj_xm", 3);
        map.put("zq_xh", 4);
        map.put("cw_xh", 2);
        map.put("tj_xh", 6);
        valList.add(map);

        value.put("valList", valList);
        System.out.println(value);
        TemplateExportParams params = new TemplateExportParams("excelTemplate/test.xlsx");
        params.setColForEach(true);
        Workbook workbook = ExcelExportUtil.exportExcel(params, value);
        ExcelUtils.downLoadExcel(new FileOutputStream(new File("C:/Users/admin/Desktop/test.xlsx")), workbook);
        workbook.close();

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
