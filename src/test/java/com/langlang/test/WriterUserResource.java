package com.langlang.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.bean.dataobject.UserResource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriterUserResource {

    public static void main(String[] args) {
        List<UserResource> list = new ArrayList<>();
        UserResource userResource = new UserResource();
        userResource.setUserId(111111);
        userResource.setEndpoint(Arrays.asList("resource A", "resource B", "resource C"));
        list.add(userResource);

        UserResource userResource1 = new UserResource();
        userResource1.setUserId(222222);
        userResource1.setEndpoint(Arrays.asList());
        list.add(userResource1);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new ClassPathResource("user-resource.json").getFile();
            objectMapper.writeValue(file, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
