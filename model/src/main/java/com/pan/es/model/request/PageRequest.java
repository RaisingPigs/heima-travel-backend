package com.pan.es.model.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import sun.font.TrueTypeFont;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-08 15:28
 **/
@Data
@ApiModel("分页请求对象")
public class PageRequest {
    @ApiModelProperty(value = "当前页码", required = false, example = "1")
    @Range(min = 1, max = 100, message = "页数必须在1~100之间")
    private int pagenum = 1;

    @ApiModelProperty(value = "单页展示数据量", required = false, example = "5")
    private int pagesize = 5;

    @ApiModelProperty(value = "搜索关键字", required = false, example = "如家")
    private String keyword;

    @ApiModelProperty(value = "排序字段", required = false, example = "")
    private String sortBy;

    @ApiModelProperty(value = "城市", required = false, example = "[上海]")
    private String[] city;

    @ApiModelProperty(value = "星级", required = false, example = "[二钻]")
    private String[] starName;

    @ApiModelProperty(value = "品牌", required = false, example = "[如家]")
    private String[] brand;

    @ApiModelProperty(value = "最小价格", required = false, example = "0")
    @Range(min = 0, max = 10000, message = "最小价格必须在0~10000之间")
    private Integer minPrice;

    @ApiModelProperty(value = "最大价格", required = false, example = "300")
    @Range(min = 0, max = 10000, message = "最大价格必须在0~10000之间")
    private Integer maxPrice;

    @ApiModelProperty(value = "经纬坐标", required = false, example = "30.8, 121.8")
    private String location;
    
    @ApiModelProperty(value = "距离", required = false, example = "10")
    private Integer distance;

    @ApiModelProperty(value = "是否为广告", required = false, example = "true")
    private boolean isAd;
}
