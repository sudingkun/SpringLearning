package com.we.springboot.excel.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.handler.BillImportHandler;
import com.we.springboot.excel.handler.ExcelVerifyHandlerImpl;
import com.we.springboot.excel.service.ExcelService;
import com.we.springboot.excel.utils.ExcelUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
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
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        TemplateExportParams params = new TemplateExportParams("excelTemplate/billTemplate.xlsx");
        params.setColForEach(true);
        Map<String, Object> map = excelService.getCustomBills();
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        ExcelUtils.downLoadExcel("bill.xlsx", response, workbook);
    }


    @RequestMapping("import")
    @ResponseBody
    public List<Bill> importExcel() {
        //把下面的文件地址改成上面载文件保存地址
        File file = new File("C:/Users/welov/Desktop/bill.xlsx");
        ImportParams importParams = new ImportParams();
        importParams.setDataHandler(new BillImportHandler());
        importParams.setVerifyHandler(new ExcelVerifyHandlerImpl());
        ExcelImportResult<Bill> result = ExcelImportUtil.importExcelMore(file, Map.class, importParams);
        return result.getList();
    }

}
