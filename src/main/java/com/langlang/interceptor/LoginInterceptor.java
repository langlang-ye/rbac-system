package com.langlang.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.bean.dataobject.User;
import com.langlang.dao.UserDao;
import com.langlang.enums.ResultCode;
import com.langlang.enums.Role;
import com.langlang.exception.BizException;
import com.langlang.exception.ValidationException;
import com.langlang.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        log.info("intercept all requests.........");

        String token = request.getHeader("Authorization");
        log.info("token = {}", token);

        if (StringUtils.isBlank(token)) {
            throw new ValidationException(ResultCode.TOKEN_NULL_EXCEPTION);
        }
        String json = Base64Util.decode(token);
        User user = objectMapper.readValue(json, User.class);

        // 验证用户和用户的角色
        Map<Integer, User> map = userDao.selectAll();
        if (!map.containsKey(user.getUserId())) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }
        if (!Role.ADMIN.equals(user.getRole()) && !Role.USER.equals(user.getRole())) {
            throw new BizException(ResultCode.ROLE_NOT_EXIST);
        }


        log.info("user = {}", json);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return true;
    }
}
