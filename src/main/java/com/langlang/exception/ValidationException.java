package com.langlang.exception;

public class ValidationException extends CommonException {

    private static final long serialVersionUID = 1L;


    public ValidationException(IResult result) {
        this(result, result.message());
    }

    public ValidationException(IResult result, String message) {
        super(result, message);
    }

    public ValidationException(String message) {
        super(IResult.ILLEGAL_ARGUMENT, message);
    }

    public ValidationException(Throwable cause) {
        super(IResult.ILLEGAL_ARGUMENT, cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(IResult.ILLEGAL_ARGUMENT, message, cause);
    }
}
