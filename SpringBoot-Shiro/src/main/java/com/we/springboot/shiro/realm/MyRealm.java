package com.we.springboot.shiro.realm;

import com.we.springboot.shiro.bean.User;
import com.we.springboot.shiro.dao.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sudingkun
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("11111");
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误！");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}
