package com.langlang.bean.req;

import lombok.Data;

import java.util.List;

@Data
public class UserResourceReq {

    private Integer userId;

    private List<String> endpoint;

}
