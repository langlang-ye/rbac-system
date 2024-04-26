package com.langlang.service;

import com.langlang.bean.dataobject.User;
import com.langlang.bean.dataobject.UserResource;
import com.langlang.bean.req.UserResourceReq;
import com.langlang.common.ResultData;
import com.langlang.dao.ResourceDao;
import com.langlang.dao.UserResourceDao;
import com.langlang.enums.ResultCode;
import com.langlang.enums.Role;
import com.langlang.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserResourceDao userResourceDao;

    @Autowired
    private ResourceDao resourceDao;


    @Override
    public ResultData<String> addUser(HttpServletRequest request, UserResourceReq userResourceReq) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole().equals(Role.USER)) {
            throw new BizException(ResultCode.PERMISSION_DENIED);
        } else {
            List<String> resources = resourceDao.selectAll();
            if (!new HashSet<>(resources).containsAll(userResourceReq.getEndpoint())) {
                throw new BizException(ResultCode.RESOURCE_NOT_EXIST);
            }
            // 添加
            Map<Integer, UserResource> userResourceMap = userResourceDao.selectAll();
            UserResource userResource = new UserResource();
            BeanUtils.copyProperties(userResourceReq, userResource);

            userResourceMap.put(userResource.getUserId(), userResource);
            return new ResultData<>("Added successfully");
        }
    }


    @Override
    public ResultData<String> checkResource(HttpServletRequest request, String resource) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();

        List<String> resources = resourceDao.selectAll();
        if (!resources.contains(resource)) {
            throw new BizException(ResultCode.RESOURCE_NOT_EXIST);
        }

        if (user.getRole().equals(Role.USER)) {
            Map<Integer, UserResource> userResourceMap = userResourceDao.selectAll();
            UserResource userResource = userResourceMap.get(userId);
            if (userResource == null || !userResource.getEndpoint().contains(resource)) {
                return new ResultData<>(String.format("Failure: %s does not have access to %s", user.getAccountName(), resource));
            }
        }
        return new ResultData<>(String.format("Success: %s has access to %s", user.getAccountName(), resource));


    }
}
