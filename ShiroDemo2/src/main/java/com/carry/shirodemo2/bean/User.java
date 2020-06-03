package com.carry.shirodemo2.bean;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    /**
     * id
     */
    private String uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 一个用户对应多个角色
     */
    private Set<Role> roles = new HashSet<>();
}
