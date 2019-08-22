package com.we.springboot.shiro.realm;

import com.we.springboot.shiro.bean.Permission;
import com.we.springboot.shiro.bean.Role;
import com.we.springboot.shiro.bean.User;
import com.we.springboot.shiro.dao.PermissionMapper;
import com.we.springboot.shiro.dao.RoleMapper;
import com.we.springboot.shiro.dao.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: sudingkun
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    @Lazy //就是这里，必须延时加载，根本原因是bean实例化的顺序上，shiro的bean必须要先实例化，否则@Cacheable注解无效
    private UserMapper userMapper;

    @Resource
    @Lazy //就是这里，必须延时加载，根本原因是bean实例化的顺序上，shiro的bean必须要先实例化，否则@Cacheable注解无效
    private RoleMapper roleMapper;

    @Resource
    @Lazy
    private PermissionMapper permissionMapper;
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

        // 获取用户权限集
        Set<Permission> permissionList = permissionMapper.findByUserName(userName);
        Set<String> permissionSet = new HashSet<>();
        for (Permission p : permissionList) {
            permissionSet.add(p.getEnname());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
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
