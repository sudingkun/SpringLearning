package com.we.springboot.redis.controller;

import com.we.springboot.redis.bean.Department;
import com.we.springboot.redis.bean.ResultDataObject;
import com.we.springboot.redis.service.DepartmentService;
import com.we.springboot.redis.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("dept")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("save")
    public ResultDataObject save(@RequestBody Department department) {
        return ResultUtil.success(departmentService.save(department));
    }

    @DeleteMapping("delete/{id}")
    public ResultDataObject delete(@PathVariable Integer id) {
        departmentService.delete(id);
        return ResultUtil.success();
    }

    @GetMapping("findById/{id}")
    public ResultDataObject findById(@PathVariable Integer id) {
        return ResultUtil.success(departmentService.findById(id));
    }

    @GetMapping("findAll")
    public ResultDataObject findAll() {
        return ResultUtil.success(departmentService.findAll());
    }

    @PutMapping("update")
    public ResultDataObject update(@RequestBody Department department) {
        return ResultUtil.success(departmentService.update(department));
    }


    @GetMapping("setSession")
    @ResponseBody
    public Department setRedisSession(HttpServletRequest request) {
        Department department = new Department();
        department.setId(66);
        department.setDepartmentName("test");
        request.getSession().setAttribute("dept", department);
        return department;
    }

    @GetMapping("getSession")
    public Map getRedisSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("dept"));
        return map;
    }


}
