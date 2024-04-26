package com.langlang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.bean.dataobject.User;
import com.langlang.bean.req.UserResourceReq;
import com.langlang.common.ResultBase;
import com.langlang.common.ResultData;
import com.langlang.enums.ResultCode;
import com.langlang.enums.Role;
import com.langlang.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 添加成功
    @Test
    void testAddUser() throws Exception {
        User user = new User();
        user.setUserId(123456);
        user.setAccountName("admin");
        user.setRole(Role.ADMIN);
        String token = TokenUtil.createToken(user);

        UserResourceReq req = new UserResourceReq();
        req.setUserId(222222);
        req.setEndpoint(Arrays.asList("resource C", "resource D", "resource E"));
        ResultData<String> resultData = new ResultData<>("Added successfully");

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(resultData)));

    }


    // 访问资源成功
    @Test
    void testCheckResource() throws Exception {
        User user = new User();
        user.setUserId(111111);
        String name = "jack";
        user.setAccountName(name);
        user.setRole(Role.USER);
        String token = TokenUtil.createToken(user);

        String resource = "resource A";
        ResultData resultData = new ResultData<>(String.format("Success: %s has access to %s", user.getAccountName(), resource));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + resource)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(resultData)));
    }


    /************************************** 用户不存在或者资源不存在权限不足 测试 ****************************************** */

    // 权限不足
    @Test
    void testAddUserFail() throws Exception {
        User user = new User();
        user.setUserId(333333);
        user.setAccountName("carl");
        user.setRole(Role.USER);
        String token = TokenUtil.createToken(user);

        UserResourceReq req = new UserResourceReq();
        req.setUserId(222222);
        req.setEndpoint(Arrays.asList("resource C", "resource D", "resource E"));
        ResultBase resultBase = new ResultBase(ResultCode.PERMISSION_DENIED);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(resultBase)));


    }

    // 资源访问失败
    @Test
    void testCheckResourceFail() throws Exception {
        User user = new User();
        user.setUserId(333333);
        String name = "carl";
        user.setAccountName(name);
        user.setRole(Role.USER);
        String token = TokenUtil.createToken(user);

        String resource = "resource A";
        ResultData resultData = new ResultData<>(String.format("Failure: %s does not have access to %s", user.getAccountName(), resource));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + resource)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(resultData)));
    }


    // 用户不存在
    @Test
    void testAddUserWithNoUser() throws Exception {
        User user = new User();
        user.setUserId(999999);
        user.setAccountName("jerry");
        user.setRole(Role.USER);
        String token = TokenUtil.createToken(user);

        UserResourceReq req = new UserResourceReq();
        req.setUserId(222222);
        req.setEndpoint(Arrays.asList("resource C", "resource D", "resource E"));
        ResultBase resultBase = new ResultBase(ResultCode.USER_NOT_EXIST);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(resultBase)));



    }

    // 角色不存在
    @Test
    void testCheckResourceWithNoRole() throws Exception {
        User user = new User();
        user.setUserId(333333);
        user.setAccountName("jerry");
        user.setRole(Role.AAA);
        String token = TokenUtil.createToken(user);

        String resource = "resource A";
        ResultBase resultBase = new ResultBase(ResultCode.ROLE_NOT_EXIST);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + resource)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(resultBase)));
    }

}
