package com.langlang.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.bean.dataobject.UserResource;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadUserResource {


    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserResource> list = null;
        try {
            File file = new ClassPathResource("user-resource.json").getFile();
            String str = new BufferedReader(new FileReader(file)).readLine();

            list = objectMapper.readValue(str, new TypeReference<ArrayList<UserResource>>(){});

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (UserResource userResource : list) {
            System.out.println(userResource);
        }
    }
}
