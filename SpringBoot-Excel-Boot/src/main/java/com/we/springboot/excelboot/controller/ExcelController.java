package com.we.springboot.excelboot.controller;

import com.excel.poi.ExcelBoot;
import com.excel.poi.entity.ErrorEntity;
import com.excel.poi.function.ExportFunction;
import com.excel.poi.function.ImportFunction;
import com.we.springboot.excelboot.bean.Bill;
import com.we.springboot.excelboot.bean.ParamEntity;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author sudingkun
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    private static List<Bill> getAll() {
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill("张三", "13012345698", new Date(), new BigDecimal(100), null));
        bills.add(new Bill("李四", "14563257789", new Date(), new BigDecimal(150), null));
        return bills;
    }

    @RequestMapping("import")
    public void importExcel() throws IOException, OpenXML4JException, SAXException {
        File file = new File("C:/Users/admin/Desktop/Excel文件名.xlsx");
        ExcelBoot.ImportBuilder(new FileInputStream(file), Bill.class)
                .importExcel(new ImportFunction<Bill>() {
                    /**
                     *
                     * @param sheetIndex 当前执行的Sheet的索引, 从1开始
                     * @param rowIndex 当前执行的行数, 从1开始
                     * @param bill Excel行数据的实体
                     */
                    @Override
                    public void onProcess(int sheetIndex, int rowIndex, Bill bill) {
                        //将读取到Excel中每一行的userEntity数据进行自定义处理
                        //如果该行数据发生问题,将不会走本方法,而会走onError方法
                        System.out.println(bill);
                    }

                    /**
                     *
                     * @param errorEntity 错误信息实体
                     */
                    @Override
                    public void onError(ErrorEntity errorEntity) {
                        //将每条数据非空和正则校验后的错误信息errorEntity进行自定义处理
                        System.out.println(errorEntity);
                    }
                });



    }


    @RequestMapping("export")
    public void export(HttpServletResponse httpServletResponse) {
        ParamEntity queryParam = new ParamEntity();
        ExcelBoot.ExportBuilder(httpServletResponse, "Excel文件名", Bill.class).exportResponse(queryParam,
                new ExportFunction<ParamEntity, Bill>() {
                    List<Bill> getData() {
                        return getAll();
                    }

                    /**
                     * @param queryParam 查询条件对象
                     * @param pageNum    当前页数,从1开始
                     * @param pageSize   每页条数,默认3000
                     * @return
                     */
                    @Override
                    public List<Bill> pageQuery(ParamEntity queryParam, int pageNum, int pageSize) {
                        System.out.println(queryParam);
                        //1.将pageNum和pageSize传入使用本组件的开发者自己项目的分页逻辑中

                        //2.调用使用本组件的开发者自己项目的分页查询方法
                        List<Bill> result = getData();

                        return result;
                    }

                    /**
                     * 将查询出来的每条数据进行转换
                     *
                     * @param o
                     */
                    @Override
                    public Bill convert(Bill o) {
                        //用于编写开发者自定义的转换逻辑
                        return o;
                    }
                });
    }

}