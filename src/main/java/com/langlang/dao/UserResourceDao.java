package com.langlang.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langlang.bean.dataobject.UserResource;
import org.springframework.beans.factory.DisposableBean;
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
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserResourceDao implements InitializingBean, DisposableBean {

    @Autowired
    private ObjectMapper objectMapper;

    private Map<Integer, UserResource> map;

    public Map<Integer, UserResource> selectAll() {
        return map;
    }

    @Override
    public void destroy() throws Exception {
        List<UserResource> list = new ArrayList<>(map.values());
        try {
            File file = new ClassPathResource("user-resource.json").getFile();
            objectMapper.writeValue(file, list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BufferedReader bufferedReader = null;
        try {
            File file = new ClassPathResource("user-resource.json").getFile();
            bufferedReader = new BufferedReader(new FileReader(file));
            String str = bufferedReader.readLine();

            List<UserResource> list = objectMapper.readValue(str, new TypeReference<ArrayList<UserResource>>(){});
            map = list.stream().collect(Collectors.toMap(UserResource::getUserId, userResource -> userResource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

    }
}
