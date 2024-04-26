package com.langlang.bean.dataobject;

import lombok.Data;

import java.util.List;

@Data
public class UserResource {

    private Integer userId;

    private List<String> endpoint;

}
