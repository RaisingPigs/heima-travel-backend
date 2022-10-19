package com.pan.es.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-08 11:06
 **/
@Data
@ApiModel("响应信息类")
public class BaseResponse<T> {
    @ApiModelProperty("响应码")
    private int code;
    @ApiModelProperty("响应数据")
    private T data;
    @ApiModelProperty("响应信息")
    private String msg;
    @ApiModelProperty("响应描述")
    private String desc;

    public BaseResponse(int code, T data, String msg, String desc) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.desc = desc;
    }

    public BaseResponse(ResultCode resultCode, T data) {
        this(resultCode.getCode(), data, resultCode.getMsg(), resultCode.getDesc());
    }

    public BaseResponse(ResultCode resultCode, String msg, String desc) {
        this(resultCode.getCode(), null, msg, desc);
    }

    public BaseResponse(ResultCode resultCode, String desc) {
        this(resultCode, resultCode.getMsg(), desc);
    }

    public BaseResponse(ResultCode resultCode) {
        this(resultCode, resultCode.getDesc());
    }
}
