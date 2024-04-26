package com.langlang.bean.dataobject;

import com.langlang.enums.Role;
import lombok.Data;

@Data
public class User {

    private Integer userId;

    private String accountName;

    private Role role;

}
