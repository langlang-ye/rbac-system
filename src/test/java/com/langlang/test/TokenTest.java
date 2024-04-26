package com.langlang.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.langlang.bean.dataobject.User;
import com.langlang.enums.Role;
import com.langlang.util.TokenUtil;

public class TokenTest {

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserId(123456);
        user.setAccountName("admin");
        user.setRole(Role.ADMIN);
        System.out.print(user + "    ");
        System.out.println(TokenUtil.createToken(user));


        user = new User();
        user.setUserId(111111);
        user.setAccountName("jack");
        user.setRole(Role.USER);
        System.out.print(user + "    ");
        System.out.println(TokenUtil.createToken(user));

        user = new User();
        user.setUserId(222222);
        user.setAccountName("tom");
        user.setRole(Role.USER);
        System.out.print(user + "    ");
        System.out.println(TokenUtil.createToken(user));

        user = new User();
        user.setUserId(333333);
        user.setAccountName("carl");
        user.setRole(Role.USER);
        System.out.print(user + "    ");
        System.out.println(TokenUtil.createToken(user));

        user = new User();
        user.setUserId(999999);
        user.setAccountName("jerry");
        user.setRole(Role.USER);
        System.out.print(user + "    ");
        System.out.println(" 不存在的用户 " + TokenUtil.createToken(user));

        user = new User();
        user.setUserId(333333);
        user.setAccountName("jerry");
        user.setRole(Role.AAA);
        System.out.print(user + "    ");
        System.out.println(" 不存在的角色 " + TokenUtil.createToken(user));


    }


}
