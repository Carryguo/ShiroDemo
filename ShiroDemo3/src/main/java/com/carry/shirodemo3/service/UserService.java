package com.carry.shirodemo3.service;

import com.carry.shirodemo3.bean.User;

public interface UserService {
    User findByUsername(String username);
}
