package com.langlang.exception;

public class BizException extends CommonException{

    private static final long serialVersionUID = 1;

    public BizException(IResult result) {
        this(result, result.message());
    }

    public BizException(IResult result, String message) {
        super(result, message);
    }

    public BizException(IResult result, Throwable cause) {
        super(result, result.message(), cause);
    }

    public BizException(IResult result, String message, Throwable cause) {
        super(result, message, cause);
    }
}
