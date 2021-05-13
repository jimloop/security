package com.jim.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CsrfController {

    @GetMapping("/toupdate")
    public String test(){
        return "csrfTest";
    }

    @PostMapping("/update_token")
    public String getToken(){
        return "csrf_token";
    }
}
