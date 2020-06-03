package com.carry.shirodemo.service;

import com.carry.shirodemo.bean.User;
import com.carry.shirodemo.mapper.Usermapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImp implements UserService {

    @Resource
    private Usermapper usermapper;

    @Override
    public User findByUsername(String username) {
        return usermapper.findByUsername(username);
    }
}
