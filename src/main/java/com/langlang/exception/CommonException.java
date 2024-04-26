package com.langlang.exception;

public class CommonException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private IResult result;

    public CommonException(IResult result) {
        this(result, result.message());
    }

    public CommonException(IResult result, String message) {
        super(message);
        this.result = result;
    }

    public CommonException(IResult result, Throwable cause) {
        this(result, result.message(), cause);
    }

    public CommonException(IResult result, String message, Throwable cause) {
        super(message, cause);
        this.result = result;
    }

    public void setResult(IResult result) {
        this.result = result;
    }

    public IResult getResult() {
        return this.result;
    }
}
