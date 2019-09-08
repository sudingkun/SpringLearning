package com.we.springboot.excel.constants;

/**
 * @author we
 */
public interface Constants {

    String CUSTOM_COLUMN = "costs";

    interface Bill {
        String NAME = "用户名";
        String PHONE = "手机号";
        String PAYMENT_DATE = "创建时间";
        String TOTAL = "总额";
    }

    interface Excel {
        interface Type {
            String XLS = "xls";
            String XLSX = "xlsx";
        }

        interface ContentType {
            String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            String XLS = "application/vnd.ms-excel";
            String DEFAULT = "application/octet-stream";
        }
    }

}
