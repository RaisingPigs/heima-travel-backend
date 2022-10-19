package com.pan.es.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-08 17:00
 **/
@Data
@ApiModel("分页数据对象")
public class PageVo<T> {
    @ApiModelProperty("总记录数")
    private int total;
    @ApiModelProperty("分页数据")
    private List<T> list;

    public PageVo() {
    }

    public PageVo(int total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}
