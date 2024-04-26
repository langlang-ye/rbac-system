package com.langlang.controller;

import com.langlang.bean.req.UserResourceReq;
import com.langlang.common.ResultData;
import com.langlang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "UserController", tags = "1.用户相关")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @PostMapping("/admin/addUser")
    public ResultData<String> addUser(HttpServletRequest request, @RequestBody UserResourceReq userResourceReq) {
        return userService.addUser(request, userResourceReq);
    }


    @ApiOperation(value = "访问资源")
    @GetMapping("/user/{resource}")
    public ResultData<String> checkResource(HttpServletRequest request, @PathVariable String resource) {
        return userService.checkResource(request, resource);
    }
}
