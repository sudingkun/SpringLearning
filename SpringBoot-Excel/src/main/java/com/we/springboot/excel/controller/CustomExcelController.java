package com.we.springboot.excel.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.core.bean.BeanUtil;
import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.handler.BillImportHandler;
import com.we.springboot.excel.handler.ExcelVerifyHandlerImpl;
import com.we.springboot.excel.service.ExcelService;
import com.we.springboot.excel.utils.ExcelUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义导入导出（物业人员可以自己新增删除列）
 *
 * @author sudingkun
 */
@Controller
@RequestMapping("custom")
@RequiredArgsConstructor
public class CustomExcelController {

    private final ExcelService excelService;

    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws UnsupportedEncodingException {
        String templateUrl = "excelTemplate/billTemplate.xlsx";
        return exportExcelByTemplate("bill", templateUrl, excelService.getCustomBills());
    }


    /**
     * 通用的excel（xls，xlsx）导入校验，导出错误excel
     *
     * @return
     */
    @RequestMapping("import")
    public ResponseEntity<byte[]> importExcel() throws UnsupportedEncodingException {
        File file = new File("C:/Users/admin/Desktop/2.xls");
        ImportParams importParams = new ImportParams();
        importParams.setDataHandler(new BillImportHandler());
        importParams.setVerifyHandler(new ExcelVerifyHandlerImpl());
        ExcelImportResult<Map<String, Object>> result = ExcelImportUtil.importExcelMore(file, Map.class, importParams);

        List<Map<String, Object>> failList = result.getFailList();

        if (!failList.isEmpty()) {
            List<Bill> bills = new ArrayList<>();
            StringBuilder error = new StringBuilder();
            for (Map<String, Object> billMap : failList) {
                //把map转换成bill放入bills
                bills.add(BeanUtil.mapToBean(billMap, Bill.class, true));
                error.append(billMap.get("excelErrorMsg"));
            }
            Map<String, Object> map = excelService.process(bills);
            map.put("error", error);

            String templateUrl = "excelTemplate/billTemplate2.xlsx";
            return exportExcelByTemplate("fail.xlsx", templateUrl, map);
        }
        return null;
    }

    /**
     * 使用模板导出excel
     *
     * @param fileName    文件名
     * @param templateUrl 模板路径
     * @param map         数据
     */
    private ResponseEntity<byte[]> exportExcelByTemplate(String fileName, String templateUrl, Map<String, Object> map) throws UnsupportedEncodingException {
        TemplateExportParams params = new TemplateExportParams(templateUrl);
        params.setColForEach(true);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        return ExcelUtils.downLoadExcel(fileName, workbook);
    }

}
