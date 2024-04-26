package com.langlang.service;

import com.langlang.bean.req.UserResourceReq;
import com.langlang.common.ResultData;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ResultData<String> addUser(HttpServletRequest request, UserResourceReq userResourceReq);


    ResultData<String> checkResource(HttpServletRequest request, String resource);
}
