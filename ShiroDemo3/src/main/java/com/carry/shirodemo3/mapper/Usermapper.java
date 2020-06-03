package com.carry.shirodemo3.mapper;

import com.carry.shirodemo3.bean.User;
import org.apache.ibatis.annotations.Param;

public interface Usermapper {
    User findByUsername(@Param("username") String username);
}
