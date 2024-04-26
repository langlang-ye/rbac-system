package com.langlang.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WriterResource {

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> list = new ArrayList<>();
        list.add("resource A");
        list.add("resource B");
        list.add("resource C");
        list.add("resource D");
        list.add("resource E");
        list.add("resource F");

        try {
            File file = new ClassPathResource("resource.json").getFile();
            objectMapper.writeValue(file, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
