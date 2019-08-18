package com.we.springboot.shiro.controller;

import com.we.springboot.shiro.bean.Result;
import com.we.springboot.shiro.bean.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: sudingkun
 * @date: 2019-08-06 16:38
 */
@Controller
public class UserController {

    @GetMapping("toLogin")
    public String login() {
        System.out.println("toLogin");
        return "login";
    }


    // shiro 有个默认接收 @PostMapping("login") 映射的方法，如果我们登入页面也是发送login请求，则下面login方法就不需要了。
    // 如果登入方法不是 login 需要添加 filterChainDefinitionMap.put("/xx", "anon");然后再写一个下面的方法处理
    @PostMapping("login")
    @ResponseBody
    public Result login(String username, String password, Boolean rememberMe) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.error(ResultCode.ACCOUNT_UNKNOWN);
        } catch (IncorrectCredentialsException e) {
            return Result.error(ResultCode.ACCOUNT_ERROR);
        }
        return Result.success();
    }

    @GetMapping("index")
    public String index() {
        System.out.println("index");
        return "index";
    }

}
