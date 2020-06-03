package com.carry.shirodemo2.config;

import com.carry.shirodemo2.matcher.CredentialMatcher;
import com.carry.shirodemo2.realm.AuthRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {
    /**
     　　* 开启shiro aop注解支持.
     　　* 使用代理方式;所以需要开启代码支持;
         * @Bean 开启代理
         * 这里的@Qualifier的作用是使用指定的service/bean,而这里的@Qualifier是指下文的 @Bean("credentialMatcher")
     　　*/
    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher")CredentialMatcher matcher){
        AuthRealm authRealm = new AuthRealm();
        //开启Cache管理
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher();
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     * @return manager
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm)
    {
        DefaultSecurityManager  manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

//         Filter工厂，设置对应的过滤条件和跳转条件
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager manager)
    {
        //System.out.print("这里有问题");
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
//        登录的url
        bean.setLoginUrl("/login");
//        登录成功后的url
//        bean.setSuccessUrl("/index");
//        显示没有授权的url
//        bean.setUnauthorizedUrl("/unauthorized");
//        拦截
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
      /*
        anon: 无需认证即可访问
        authc: 需要认证才可访问
        user: 点击“记住我”功能可访问
        perms: 拥有权限才可以访问
        role: 拥有某个角色权限才能访问
        */

//        需要认证才可访问
//        filterChainDefinitionMap.put("/index","authc");
//        无需认证即可访问
//        filterChainDefinitionMap.put("/login","anon");
//        filterChainDefinitionMap.put("/loginUser","anon");
//        filterChainDefinitionMap.put("/admin","roles[admin]");
//        filterChainDefinitionMap.put("/edit","perms[edit]");
        filterChainDefinitionMap.put("/admin","authc");
        filterChainDefinitionMap.put("/edit","authc");
        filterChainDefinitionMap.put("/**","anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

}
