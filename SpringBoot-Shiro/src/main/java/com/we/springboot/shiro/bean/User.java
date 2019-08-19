package com.we.springboot.shiro.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.io.Serializable;

@Data
@TableName(value = "shiro_user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    /**
     * 密码，加密存储
     * String password = new SimpleHash("md5", "123456", "admin", 2).toString();
     */
    private String password;

    /**
     * 加密的盐(使用用户名作为盐)
     */
    private String salt;


    public static void main(String[] args) {
        System.out.println(new SimpleHash("md5", "123456", "root", 2).toString());
    }

}