package com.we.springboot.swagger.controller;


import com.we.springboot.swagger.bean.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author we
 */
@Slf4j
@Api("用户crud的api")
@RestController
@RequestMapping("user2")
public class User2Controller {


    @PostMapping("update")
    @ApiOperation(value = "修改用户信息", notes = "修改用户的姓名")
    @ApiImplicitParam(name = "name", value = "需要修改的姓名")
    public User updateUser(@RequestParam("name") String name, @RequestBody User user) {
        user.setName(name);
        return user;
    }

    /**
     * @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1") 一般不用 defaultValue
     * @RequestParam 可以省略效果一样
     */
    @GetMapping("save")
    @ApiOperation("新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1"),
            @ApiImplicitParam(name = "name", value = "姓名", example = "tom")
    }
    )
    public User saveUser(Integer id, @RequestParam String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }


    @ApiOperation(value = "删除用户", notes = "根据id删除对应的用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没有填好"),
            @ApiResponse(code = 404, message = "请求路径没有找到")
    })
    @DeleteMapping("delete/{id}")
    public Integer deleteUser(@PathVariable Integer id) {
        return id;
    }

    @ApiIgnore
    @GetMapping("apiIgnore/")
    public String testApiImplicitParams() {
        return "success";
    }

}
