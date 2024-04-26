package com.langlang.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadResource {

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> list;

        try {
            File file = new ClassPathResource("resource.json").getFile();
            String str = new BufferedReader(new FileReader(file)).readLine();

            list = objectMapper.readValue(str, new TypeReference<ArrayList<String>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String str : list) {
            System.out.println(str);
        }


    }
}
