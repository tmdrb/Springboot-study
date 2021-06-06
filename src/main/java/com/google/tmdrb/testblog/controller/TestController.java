package com.google.tmdrb.testblog.controller;


import com.google.tmdrb.testblog.model.MUser;

import com.google.tmdrb.testblog.repository.UserRepo;
import com.google.tmdrb.testblog.service.TestService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController

public class TestController {

    @Autowired
    TestService1 testService1;

    @Autowired
    UserRepo userRepo;


    @GetMapping("/http/get")
    //@requestparm 으로 쿼리스트링 값을 하나하나 받는것 보다 객체를 보내면 한번에 매핑된다.
    //spring이 매핑되도록 도와준다.
    public String getTest(@RequestParam MUser user){
        System.out.println(user);
        return "get요청";
    }
    @PostMapping("/http/post")
    //post는 body에 데이터 전송한다.
    //body 타입에서 전송하는
    public String postTest(@RequestBody MUser user){
        System.out.println(user);
        testService1.insert(user);

        return "post요청";
    }
    @PutMapping("/http/put")
    public String putTest(@RequestBody MUser user){
        testService1.update(user);

        return "put요청";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(@RequestBody HashMap<String, String> map){

        return "delete요청";
    }
}
