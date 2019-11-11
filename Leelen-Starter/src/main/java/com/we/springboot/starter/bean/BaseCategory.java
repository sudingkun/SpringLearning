package com.we.springboot.starter.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author admin
 */
@TableName("pms_category")
@Data
@ToString
public class BaseCategory implements Serializable {

    private static final long serialVersionUID = -7662805959061292342L;

    private Long id;

    /**
     * 菜单服务对象 1：物业公司 2：小区物业
     */
    private Boolean categoryFor;

    /**
     * 菜单标识
     */
    private String categorySign;

    /**
     * 菜单名称
     */
    private String categoryName;

    /**
     * 父级菜单标识
     */
    private String categoryParentSign;

    /**
     * 菜单所属系统
     */
    private String categorySys;

    /**
     * 菜单图标
     */
    private String categoryIcon;

    /**
     * 菜单url
     */
    private String categoryUrl;

    /**
     * 菜单别名
     */
    private String categoryAlias;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 菜单状态1：正常2：注销
     */
    private Integer categoryStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建人姓名
     */
    private String createByName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private List<BaseCategory> children = new ArrayList<>();
}