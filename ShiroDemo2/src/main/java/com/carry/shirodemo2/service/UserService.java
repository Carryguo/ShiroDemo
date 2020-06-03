package com.carry.shirodemo2.service;

import com.carry.shirodemo2.bean.User;

public interface UserService {
    User findByUsername(String username);
}
