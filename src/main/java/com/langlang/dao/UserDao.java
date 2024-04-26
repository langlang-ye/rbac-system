package com.langlang.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.bean.dataobject.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserDao implements InitializingBean {

    @Autowired
    private ObjectMapper objectMapper;

    private Map<Integer, User> map;

    public Map<Integer, User> selectAll() {
        return map;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BufferedReader bufferedReader = null;
        try {
            File file = new ClassPathResource("user.json").getFile();
            bufferedReader = new BufferedReader(new FileReader(file));
            String str = bufferedReader.readLine();
            List<User> list = objectMapper.readValue(str, new TypeReference<ArrayList<User>>() {});

            map = list.stream().collect(Collectors.toMap(User::getUserId, user -> user));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

    }
}
