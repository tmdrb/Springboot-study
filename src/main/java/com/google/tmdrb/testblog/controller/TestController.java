package com.google.tmdrb.testblog.controller;


import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {

    @GetMapping("/http/get")
    public String getTest(){
        System.out.println("get");
        return "get요청";
    }
    @PostMapping("/http/post")
    public String postTest(){

        return "post요청";
    }
    @PutMapping("/http/put")
    public String putTest(){

        return "put요청";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){

        return "delete요청";
    }
}
