package com.we.springboot.excel.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.we.springboot.excel.bean.Bill;
import com.we.springboot.excel.handler.MapImportHandler;
import com.we.springboot.excel.utils.EasyPoiUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
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
        bills.add(new Bill("张三", "110", "2019-09-03", null, 100D));
        bills.add(new Bill("李四", "120", "2019-09-03", null, 110D));
        bills.add(new Bill("王五", "119", "2019-09-03", null, 120D));
        return bills;
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response) {
        EasyPoiUtils.exportExcel(getAll(), "立林小区八月份账单", "账单", Bill.class, "8月份账单.xls", response);
    }

    @RequestMapping("/import")
    @ResponseBody
    public List<Bill> importUsers() throws Exception {

        File file = new File("C:/Users/welov/Desktop/8月份账单.xls");
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setDataHandler(new MapImportHandler());
        List<Map<String, Object>> data = ExcelImportUtil.importExcel(file, Map.class, importParams);
        List<Bill> bills = new ArrayList<>();
        for (Map<String, Object> bill : data) {
            Bill b = new Bill();
            BeanUtils.populate(b, bill);
            bills.add(b);
        }
        System.out.println(bills);
        return bills;
    }

}
