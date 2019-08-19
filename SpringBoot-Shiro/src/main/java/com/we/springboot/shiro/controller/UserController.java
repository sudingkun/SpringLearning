package com.we.springboot.shiro.controller;

import com.we.springboot.shiro.bean.Result;
import com.we.springboot.shiro.bean.ResultCode;
import com.we.springboot.shiro.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: sudingkun
 * @date: 2019-08-06 16:38
 */
@Controller
public class UserController {

    @GetMapping({"toLogin","/"})
    public String login() {
        System.out.println("toLogin");
        return "login";
    }


    @PostMapping("login")
    @ResponseBody
    public Result login(String username, String password, Boolean rememberMe) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
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
    public String index(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        System.out.println("index");
        return "index";
    }

    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("value", "新增用户");
        return "user";
    }

}
