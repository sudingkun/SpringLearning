package com.we.springboot.excel.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import com.we.springboot.excel.bean.Bill;
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
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sudingkun
 */
@Controller
@RequestMapping("defaultExcel")
@RequiredArgsConstructor
public class DefaultExcelController {

    private final ExcelService excelService;

    private void initExportParams(ExportParams params) {
        params.setSheetName("账单");
        params.setTitle("九月份账单");
        params.setType(ExcelType.XSSF);
    }

    private File getFile() throws IOException {
        return new ClassPathResource("excelTemplate/excelTemplate.xlsx").getFile();
    }

    /**
     * 导出指定模板
     */
    @RequestMapping("exportTemplate")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + getFile().getName());
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(new FileInputStream(getFile()), outputStream);
        outputStream.close();
    }

    /**
     * 导出对象对应的excel模板（web）
     */
    @RequestMapping("exportTemplate2")
    public void exportTemplate2(HttpServletResponse response) {
        //设置导出参数
        ExportParams params = new ExportParams();
        initExportParams(params);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, new ArrayList<>());
        ExcelUtils.downLoadExcel("立林小区9月份账单.xlsx", response, workbook);
    }

    /**
     * 导出对象对应的excel模板
     */
    @RequestMapping("exportTemplate3")
    public void exportTemplate3() throws IOException {
        //设置导出参数
        ExportParams params = new ExportParams();
        initExportParams(params);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, new ArrayList<>());
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/admin/Desktop/" + getFile().getName());
        ExcelUtils.downLoadExcel(fileOutputStream, workbook);
    }

    /**
     * 以流的形式导出对象对应的excel模板（web）
     * 可以给别的模块调用
     */
    @RequestMapping("exportTemplate4")
    public ResponseEntity<byte[]> exportTemplate4() throws IOException {
        //设置导出参数
        ExportParams params = new ExportParams();
        initExportParams(params);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, new ArrayList<>());
        return ExcelUtils.downLoadExcel(getFile().getName(), workbook);
    }

    /**
     * 导出数据和上面的一样
     */
    @RequestMapping("exportData")
    public void exportData(HttpServletResponse response) {
        //设置导出参数
        ExportParams params = new ExportParams();
        initExportParams(params);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Bill.class, excelService.getAll());
        ExcelUtils.downLoadExcel("立林小区8月份账单.xlsx", response, workbook);
    }


    /**
     * todo 导入校验
     */
    @RequestMapping("import")
    @ResponseBody
    public List<Bill> importExcel() {
        File file = new File("C:/Users/admin/Desktop/立林小区8月份账单.xlsx");
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        return ExcelImportUtil.importExcel(file, Bill.class, importParams);
    }


    /**
     * web文件上传模式
     * todo 导入校验
     */
    @PostMapping("import2")
    @ResponseBody
    public List<Bill> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        return ExcelImportUtil.importExcel(file.getInputStream(), Bill.class, importParams);
    }

    /**
     * 模拟发送http post 请求
     */
    @RequestMapping("http/import2")
    public void httpUpload() {
        String url = "http://localhost:8080/defaultExcel/import2";
        File file = new File("C:/Users/admin/Desktop/立林小区8月份账单.xlsx");
        HttpRequest request = HttpRequest.post(url)
                .form("file", FileUtil.file(file));
        request.executeAsync();
    }

}
