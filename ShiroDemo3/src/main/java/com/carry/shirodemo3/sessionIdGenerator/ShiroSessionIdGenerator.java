package com.carry.shirodemo3.sessionIdGenerator;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

public class ShiroSessionIdGenerator implements SessionIdGenerator {
    /**
     * 实现SessionId生成
     */
    @Override
    public Serializable generateId(Session session) {
//        System.out.println("ShiroSessionIdGenerator works");
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format("login_token_%s", sessionId);
    }
}

