package com.we.springboot.swagger.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author we
 */
@Data
@ApiModel(description = "房子实体类")
public class House {

    @ApiModelProperty(value = "房子地址")
    private String location;

    @ApiModelProperty(value = "房子有几个大门")
    private List<String> doors;

}
