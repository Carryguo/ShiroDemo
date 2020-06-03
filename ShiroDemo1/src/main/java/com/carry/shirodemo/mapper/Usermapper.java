package com.carry.shirodemo.mapper;

import com.carry.shirodemo.bean.User;
import org.apache.ibatis.annotations.Param;

public interface Usermapper {
    User findByUsername(@Param("username")String username);
}
