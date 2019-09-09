/**
 * Copyright 2013-2015 JueYue (qrb.jueyue@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.we.springboot.excel.handler;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * 自定义excel验证处理器
 *
 * @author welov
 */
public class ExcelVerifyHandlerImpl implements IExcelVerifyHandler<Map<String, Object>> {
    private static final String PATTERN = "^(0|[1-9][0-9]*)(\\.\\d+)?$";


    @Override
    public ExcelVerifyHandlerResult verifyHandler(Map<String, Object> obj) {
        boolean result = false;
        StringBuilder failMsg = new StringBuilder();
        JSONObject costs = (JSONObject) obj.get("costs");
        for (Map.Entry<String, Object> entry : costs.entrySet()) {
            if (entry.getValue() != null) {
                if (!Pattern.matches(PATTERN, entry.getValue().toString())) {
                    failMsg.append(entry.getKey()).append("输入错误");
                }
            }
        }
        if (StringUtils.isBlank(failMsg)) {
            result = Boolean.TRUE;
        }
        return new ExcelVerifyHandlerResult(result, failMsg.toString());
    }
}
