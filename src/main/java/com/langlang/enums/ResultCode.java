package com.langlang.enums;

import com.langlang.exception.IResult;

public enum ResultCode implements IResult {

    TOKEN_NULL_EXCEPTION("100001", "Token does not exist"),
    USER_NOT_EXIST("100002", "User does not exist"),
    ROLE_NOT_EXIST("100003", "Role does not exist"),
    PERMISSION_DENIED("100004", "Permission denied, Only admins can access this endpoint"),
    RESOURCE_NOT_EXIST("100005", "Resource does not exist"),



    ;




    private String code;
    private String msg;

    // 构造方法
    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code() {
        return code;
    }

    public String message() {
        return msg;
    }


}
