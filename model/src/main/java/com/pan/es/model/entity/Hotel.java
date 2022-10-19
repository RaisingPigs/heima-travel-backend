package com.pan.es.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

/**
 * 
 * @TableName tb_hotel
 */
@TableName(value ="tb_hotel")
@Data
public class Hotel implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -8602737051550092912L;
    
    /**
     * 酒店id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 酒店名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 酒店地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 酒店价格
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 酒店评分
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 酒店品牌
     */
    @TableField(value = "brand")
    private String brand;

    /**
     * 所在城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 酒店星级，1星到5星，1钻到5钻
     */
    @TableField(value = "star_name")
    private String starName;

    /**
     * 商圈
     */
    @TableField(value = "business")
    private String business;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 酒店图片
     */
    @TableField(value = "pic")
    private String pic;
}