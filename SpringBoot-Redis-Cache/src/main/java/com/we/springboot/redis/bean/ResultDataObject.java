package com.we.springboot.redis.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 返回消息体
 * @author admin
 * @param <T>
 */
@Data
public class ResultDataObject<T> {
    /***错误代码**/
    @JsonProperty(value = "Code")
    private Integer code;
    /**消息**/
    @JsonProperty(value = "Message")
    private String message;
    /**消息体**/
    @JsonProperty(value = "Data")
    private T data;

}