package com.jim.security.controller;

import com.jim.security.entity.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello security";
    }

    @GetMapping("/index")
    public String index(){
        return "hello index";
    }

    /**
     * 只有存在响应角色才能访问
     * @return
     */
    @GetMapping("/update")
    @Secured({"ROLE_sales","ROLE_admins"})
    public String update(){
        return "hello update";
    }

    /**
     * 书写表达式，判断结果返回true才能调用
     * @return
     */
    @GetMapping("/preUpdate")
    @PreAuthorize("hasAnyAuthority('admins')")
    public String update1(){
        return "hello preAuthorize";
    }

    @GetMapping("/postUpdate")
    @PostAuthorize("hasAnyAuthority('admin')")
    public String postUpdate(){
        return "postUpdate";
    }

    @GetMapping("/getAll")
    @PostAuthorize("hasAnyAuthority('admins')")
    @PostFilter("filterObject.nickname != 'sharding1'")
    public List<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        users.add(User.builder().nickname("sharding").build());
        users.add(User.builder().nickname("sharding1").build());
        users.add(User.builder().nickname("sharding2").build());
        return users;
    }

    @PostMapping("/setUsers")
    @PostAuthorize("hasAnyAuthority('admins')")
    @PreFilter("filterObject.nickname == 'sharding1'")
    public List<User> setUsers(@RequestBody List<User> users){
        return users;
    }
}
