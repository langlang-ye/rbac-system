package com.langlang.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.bean.dataobject.User;
import com.langlang.enums.Role;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WriterUser {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserId(123456);
        user.setAccountName("admin");
        user.setRole(Role.ADMIN);
        list.add(user);

        User user1 = new User();
        user1.setUserId(111111);
        user1.setAccountName("jack");
        user1.setRole(Role.USER);
        list.add(user1);

        User user2 = new User();
        user2.setUserId(222222);
        user2.setAccountName("tom");
        user2.setRole(Role.USER);
        list.add(user2);

        User user3 = new User();
        user3.setUserId(333333);
        user3.setAccountName("carl");
        user3.setRole(Role.USER);
        list.add(user3);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new ClassPathResource("user.json").getFile();
            objectMapper.writeValue(file, list);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
