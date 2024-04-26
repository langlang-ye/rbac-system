package com.langlang.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.bean.dataobject.User;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadUser {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = null;
        try {
            File file = new ClassPathResource("user.json").getFile();
            String str = new BufferedReader(new FileReader(file)).readLine();
            users = objectMapper.readValue(str, new TypeReference<ArrayList<User>>(){});

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (User user : users) {
            System.out.println(user);
        }
    }
}
