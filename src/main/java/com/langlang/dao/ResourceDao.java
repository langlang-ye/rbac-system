package com.langlang.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResourceDao implements InitializingBean {

    @Autowired
    private ObjectMapper objectMapper;

    private List<String> list;

    public List<String> selectAll() {
        return list;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BufferedReader bufferedReader = null;
        try {
            File file = new ClassPathResource("resource.json").getFile();
            bufferedReader = new BufferedReader(new FileReader(file));
            String str = bufferedReader.readLine();

            list = objectMapper.readValue(str, new TypeReference<ArrayList<String>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null){
                bufferedReader.close();
            }
        }

    }
}
