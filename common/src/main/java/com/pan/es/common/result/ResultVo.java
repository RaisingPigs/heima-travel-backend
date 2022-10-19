package com.pan.es.common.result;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-08 11:06
 **/
public class ResultVo {
    /*成功方法*/
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(ResultCode.SUCCESS, data);
    }

    /*全参的失败方法*/
    public static <T> BaseResponse<T> failed(int code, String msg, String desc) {
        return new BaseResponse<>(code, null, msg, desc); 
    }

    /*使用resultCode的失败方法*/
    public static <T> BaseResponse<T> failed(ResultCode resultCode, String msg, String desc) {
        return new BaseResponse<>(resultCode, msg, desc);
    }

    /*使用resultCode, 缺少msg的失败方法*/
    public static <T> BaseResponse<T> failed(ResultCode resultCode, String desc) {
        return new BaseResponse<>(resultCode, desc);
    }

    /*使用resultCode, 缺少msg和desc的失败方法*/
    public static <T> BaseResponse<T> failed(ResultCode resultCode) {
        return new BaseResponse<>(resultCode);
    }
}
