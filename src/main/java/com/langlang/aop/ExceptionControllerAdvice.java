package com.langlang.aop;

import com.langlang.common.ResultBase;
import com.langlang.exception.BizException;
import com.langlang.exception.IResult;
import com.langlang.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultBase global(Exception e) {
        if (e instanceof IllegalArgumentException)
            return exceptionHandle((IllegalArgumentException)e);
        if (e instanceof MethodArgumentNotValidException)
            return exceptionHandle((MethodArgumentNotValidException)e);
        if (e instanceof ValidationException)
            return exceptionHandle((ValidationException)e);
        if (e instanceof BizException)
            return exceptionHandle((BizException)e);
        return exceptionHandle(e);
    }

    private ResultBase exceptionHandle(IllegalArgumentException e) {
        String err = e.getMessage();
        log.warn("校验异常: ", e);
        return analysis(IResult.ILLEGAL_ARGUMENT.code(), err);
    }

    private ResultBase exceptionHandle(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        errorMsg.replace(errorMsg.lastIndexOf(";"), errorMsg.length(), "");
        String err = errorMsg.toString();
        log.warn("校验参数异常: {}", err);
        return analysis(IResult.ILLEGAL_ARGUMENT.code(), IResult.ILLEGAL_ARGUMENT.message());
    }

    private ResultBase exceptionHandle(ValidationException e) {
        log.warn("校验异常: ", e);
        return analysis(e.getResult().code(), (e.getMessage() == null) ? e.getResult().message() : e.getMessage());
    }

    private ResultBase exceptionHandle(BizException e) {
        log.warn("业务异常: ", e);
        return analysis(e.getResult().code(), (e.getMessage() == null) ? e.getResult().message() : e.getMessage());
    }

    private ResultBase exceptionHandle(Exception e) {
        log.error("系统未知异常: ", e);
        return analysis(IResult.SYSTEM_ERROR.code(),
                IResult.SYSTEM_ERROR.message());
    }

    private ResultBase analysis(String code, String msg) {
        ResultBase rentResult = new ResultBase(false, code, msg);
        rentResult.setSuccess(false);
        return rentResult;
    }




}
