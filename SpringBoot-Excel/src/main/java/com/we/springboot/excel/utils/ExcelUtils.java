package com.we.springboot.excel.utils;


import com.google.common.io.Files;
import com.we.springboot.excel.constants.Constants;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author sudingkun
 */
public class ExcelUtils {
    private ExcelUtils() {
    }

    /**
     * 然后下载excel（xls，xlsx）
     *
     * @param fileName 文件名
     * @param response 响应
     * @param workbook excel内容
     */
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        String contentType = Constants.Excel.ContentType.XLS;
        if (Constants.Excel.Type.XLSX.equals(getFileType(fileName).toLowerCase())) {
            contentType = Constants.Excel.ContentType.XLSX;
        }
        try {
            response.setHeader("content-Type", contentType);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文件类型
     *
     * @param fileName 文件名
     * @return excel 文件类型(xls,xlsx)
     */
    private static String getFileType(String fileName) {
        return Files.getFileExtension(fileName);
    }

}
