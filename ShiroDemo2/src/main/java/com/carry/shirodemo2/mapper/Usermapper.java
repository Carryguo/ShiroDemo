package com.carry.shirodemo2.mapper;

import com.carry.shirodemo2.bean.User;
import org.apache.ibatis.annotations.Param;

public interface Usermapper {
    User findByUsername(@Param("username") String username);
}
