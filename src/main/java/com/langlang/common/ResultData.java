package com.langlang.common;

import com.langlang.exception.IResult;

public class ResultData<E> extends ResultBase {

    private static final long serialVersionUID = -351414120670006950L;

    private E data;

    public ResultData() {}

    public ResultData(IResult result) {
        super(result);
    }

    public ResultData(E data) {
        this.data = data;
    }

    public ResultData(String code, String msg) {
        super(IResult.SUCCESS.code().equals(code), code, msg);
    }

    public ResultData<E> setIResult(IResult resultCode) {
        super.setIResult(resultCode);
        return this;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E value) {
        this.data = value;
    }
}
