package com.google.tmdrb.testblog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/auth/login")
    public String getLoginPage(){
        System.out.println("ghh");
        return "hello.html";
    }

}
