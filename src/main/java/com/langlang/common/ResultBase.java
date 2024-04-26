package com.langlang.common;

import com.langlang.exception.IResult;

import java.io.Serializable;

public class ResultBase implements Serializable {

    private static final long serialVersionUID = 1L;


    private boolean success = true;

    private String code;

    private String message;

    public ResultBase() {}

    public static ResultBase success() {
        return new ResultBase();
    }

    public static ResultBase fail(IResult result) {
        return new ResultBase(result);
    }

    public ResultBase(IResult result) {
        ResultBase(result);
    }

    public ResultBase(String code, String message) {
        ResultBase(IResult.SUCCESS.code().equals(code), code, message);
    }

    public ResultBase(boolean success, String code, String message) {
        ResultBase(success, code, message);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBase setIResult(IResult resultCode) {
        ResultBase(resultCode);
        return this;
    }

    protected final void ResultBase(IResult result) {
        this.success = (IResult.SUCCESS == result);
        this.code = result.code();
        this.message = result.message();
    }

    public final void ResultBase(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
