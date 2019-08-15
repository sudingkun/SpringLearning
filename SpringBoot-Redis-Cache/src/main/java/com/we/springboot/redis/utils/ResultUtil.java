package com.we.springboot.redis.utils;


import com.we.springboot.redis.bean.ResultDataObject;

/**
 * 定义统一返回结果格式工具
 */
public class ResultUtil {

    /**
     * 返回成功带参数
     * @param object
     * @return
     */
    public static ResultDataObject success(Object object){
        ResultDataObject resultObject = new ResultDataObject();
        resultObject.setCode(0);
        resultObject.setMessage("成功");
        resultObject.setData(object);
        return resultObject;
    }

    public static ResultDataObject success(int code, String msg, Object object){
        ResultDataObject resultObject = new ResultDataObject();
        resultObject.setCode(code);
        resultObject.setMessage(msg);
        resultObject.setData(object);
        return resultObject;
    }


    /**
     * 返回成功
     * @return
     */
    public static ResultDataObject success(){
        return success(null);
    }

    /**
     * 定义错误返回格式
     * @param code
     * @param msg
     * @return
     */
    public static ResultDataObject error(Integer code , String msg){
        ResultDataObject resultObject = new ResultDataObject();
        resultObject.setCode(code);
        resultObject.setMessage(msg);
        return resultObject;
    }
    public static ResultDataObject errorData(Integer code , String msg,Object object){
        ResultDataObject resultObject = new ResultDataObject();
        resultObject.setCode(code);
        resultObject.setMessage(msg);
        resultObject.setData(object);
        return resultObject;
    }
}