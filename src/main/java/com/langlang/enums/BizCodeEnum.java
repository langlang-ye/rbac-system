package com.langlang.enums;

/**
 * 错误码和错误信息
 */
public enum BizCodeEnum {

    TOKEN_NULL_EXCEPTION(10001,"token 不能为空");

    private int code;
    private String message;

    BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }




}
