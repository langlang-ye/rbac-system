package com.langlang.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.common.ResultBase;
import com.langlang.enums.ResultCode;

public class ErrorTest {

    public static void main(String[] args) {

        ResultBase resultBase = new ResultBase(ResultCode.PERMISSION_DENIED);
        System.out.println(resultBase);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(resultBase);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
