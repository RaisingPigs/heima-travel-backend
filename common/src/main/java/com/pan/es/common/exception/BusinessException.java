package com.pan.es.common.exception;

import com.pan.es.common.result.ResultCode;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-08 11:18
 **/
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 7419351837938618570L;

    private final int code;
    private final String desc;

    public BusinessException(int code, String msg, String desc) {
        super(msg);
        
        this.code = code;
        this.desc = desc;
    }

    public BusinessException(ResultCode resultCode, String desc) {
        this(resultCode.getCode(), resultCode.getMsg(), desc);
    }
    
    public BusinessException(ResultCode resultCode) {
        this(resultCode, resultCode.getDesc());
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
