package com.we.springboot.excel.utils;


import cn.afterturn.easypoi.exception.excel.ExcelExportException;
import com.google.common.io.Files;
import com.we.springboot.excel.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author sudingkun
 */
public class ExcelUtils {
    private ExcelUtils() {
    }

    /**
     * 获取文件类型（后缀）
     *
     * @param fileName 文件名
     * @return excel 文件类型(xls,xlsx)
     */
    private static String getFileType(String fileName) {
        return Files.getFileExtension(fileName);
    }

    /**
     * 获取excel不同格式的contentType
     *
     * @param fileName 文件名
     */
    private static String getContentType(String fileName) {
        String contentType = Constants.Excel.ContentType.XLSX;
        if (Constants.Excel.Type.XLS.equals(getFileType(fileName).toLowerCase())) {
            contentType = Constants.Excel.ContentType.XLS;
        }
        return contentType;
    }

    /**
     * 获得导出excel格式配置header
     *
     * @param fileName 文件名
     */
    private static HttpHeaders getExcelHeader(String fileName) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(getFileType(fileName))) {
            fileName += "." + Constants.Excel.Type.XLSX;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", getContentType(fileName));
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        return headers;
    }

    /**
     * 下载excel（xls，xlsx）
     *
     * @param fileName 文件名
     * @param response 响应
     * @param workbook excel内容
     */
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(getFileType(fileName))) {
            fileName += "." + Constants.Excel.Type.XLSX;
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", getContentType(fileName));
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        try (ServletOutputStream out = response.getOutputStream()) {
            workbook.write(out);
        } catch (IOException e) {
            throw new ExcelExportException(e.getMessage());
        }
    }

    /**
     * @param fileOutputStream 输出流
     * @param workbook         excel内容
     */
    public static void downLoadExcel(FileOutputStream fileOutputStream, Workbook workbook) {
        try {
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new ExcelExportException(e.getMessage());
        }
    }

    /**
     * @param fileName 文件名
     * @param workbook excel内容
     */
    public static ResponseEntity<byte[]> downLoadExcel(String fileName, Workbook workbook) throws UnsupportedEncodingException {
        byte[] body;
        try {
            //将excel内容写入outputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            //构造一个输入流
            ByteArrayInputStream in = new ByteArrayInputStream(outputStream.toByteArray());
            //将输入流中的内容写到byte数组中
            body = new byte[in.available()];
            in.read(body);
        } catch (IOException e) {
            throw new ExcelExportException(e.getMessage());
        }
        return new ResponseEntity<>(body, getExcelHeader(fileName), HttpStatus.OK);
    }

}
