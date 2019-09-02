package com.we.springboot.swagger.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author we
 */
@Data
@ApiModel(description = "用户实体类")
public class User {
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(value = "姓名", required = true, example = "tom")
    private String name;

    @ApiModelProperty(value = "年龄", required = true, example = "13")
    private Integer age;

    @ApiModelProperty(value = "房子")
    private House house;

}
