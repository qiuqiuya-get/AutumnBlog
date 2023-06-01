package com.autumn.exception;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/6/1 16:25
 */

import com.autumn.enums.AppHttpCodeEnum;

/**
 * @Author qiuqiuya
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}