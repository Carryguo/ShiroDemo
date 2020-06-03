package com.carry.shirodemo.service;

import com.carry.shirodemo.bean.User;

public interface UserService {
    User findByUsername(String username);
}
