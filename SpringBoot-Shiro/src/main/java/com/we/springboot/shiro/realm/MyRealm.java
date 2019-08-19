package com.we.springboot.shiro.realm;

import com.we.springboot.shiro.bean.Role;
import com.we.springboot.shiro.bean.User;
import com.we.springboot.shiro.dao.RoleMapper;
import com.we.springboot.shiro.dao.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: sudingkun
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUsername();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<Role> roleList = roleMapper.findByUserName(userName);
        Set<String> roleSet = new HashSet<>();
        for (Role r : roleList) {
            roleSet.add(r.getEnName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     * 密码使用MD5加密后，下面的代码可以不要了，把密码对比交给密码比较器。
     * <p>
     * String password = new String((char[]) token.getCredentials());
     * if (!password.equals(user.getPassword())) {
     * throw new IncorrectCredentialsException("密码错误！");
     * }
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

}
