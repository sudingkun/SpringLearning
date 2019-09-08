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
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 默认导入导出（excel没有自己新增列）
 *
 * @author sudingkun
 */
@Controller
@RequestMapping("excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    /**
     * 设置基本的导出参数
     */
    private void initExportParams(ExportParams params) {
        params.setSheetName("2019-9");
        params.setTitle("九月份账单");
        //导出类型xls、xlsx
        params.setType(ExcelType.XSSF);
    }

    private File getFile() throws IOException {
        return new ClassPathResource("excelTemplate/excelTemplate.xlsx").getFile();
    }

    /**
     * 导出指定模板（web）
     */
    @RequestMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + getFile().getName());
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream inputStream = new FileInputStream(getFile());
        IOUtils.copy(inputStream, outputStream);
        outputStream.close();
        inputStream.close();
    }

    /**
     * 导出对象对应的excel模板（web）
     */
    @RequestMapping("export2")
    public void export2(HttpServletResponse response) throws IOException {
        ExportParams params = new ExportParams();
        initExportParams(params);

        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, new ArrayList<>());
        ExcelUtils.downLoadExcel("9月份账单.xlsx", response, workbook);
    }


    /**
     * 导出对象对应的excel模板
     */
    @RequestMapping("export3")
    @ResponseBody
    public String export3() throws IOException {
        ExportParams params = new ExportParams();
        initExportParams(params);

        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, new ArrayList<>());
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/welov/Desktop/" + getFile().getName());
        ExcelUtils.downLoadExcel(fileOutputStream, workbook);
        return "success";
    }

    /**
     * 以流的形式导出对象对应的excel模板（web）
     */
    @RequestMapping("export4")
    public ResponseEntity<byte[]> export4() throws IOException {
        ExportParams params = new ExportParams();
        initExportParams(params);

        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, new ArrayList<>());
        return ExcelUtils.downLoadExcel(getFile().getName(), workbook);
    }

    /**
     * 导出数据和上面的一样
     */
    @RequestMapping("exportData")
    public void exportData(HttpServletResponse response) throws IOException {
        ExportParams params = new ExportParams();
        initExportParams(params);

        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, excelService.getBillList());
        ExcelUtils.downLoadExcel("8月份账单.xlsx", response, workbook);
    }


    @RequestMapping("import")
    @ResponseBody
    public void importExcel(HttpServletResponse response) throws IOException {
        File file = new File("C:/Users/welov/Desktop/8月份账单.xlsx");
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        //开启校验，根据实体类的hibernate校验注解
        importParams.setNeedVerify(true);
        ExcelImportResult<ExcelVerifyEntityOfMode> result = ExcelImportUtil.importExcelMore(file, ExcelVerifyEntityOfMode.class, importParams);
        //导入成功的数据可以写入数据库
        //导入失败的数据可以导出给用户（异步实现）
        if (!result.getFailList().isEmpty()) {
            ExcelUtils.downLoadExcel("fail.xlsx", response, result.getFailWorkbook());
        }
    }


    /**
     * web文件上传
     */
    @PostMapping("import2")
    @ResponseBody
    public void importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setNeedVerify(true);
        ExcelImportResult<ExcelVerifyEntityOfMode> result = ExcelImportUtil.importExcelMore(file.getInputStream(), ExcelVerifyEntityOfMode.class, importParams);
        ExcelUtils.downLoadExcel("fail.xlsx", response, result.getFailWorkbook());
    }


}
