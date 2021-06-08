package com.google.tmdrb.testblog.controller;


import com.google.tmdrb.testblog.model.Board;
import com.google.tmdrb.testblog.model.MUser;

import com.google.tmdrb.testblog.model.Role;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;


@RestController
public class TestController {

    @Autowired //의존성 주입
    private UserRepository userRepository;

    @GetMapping("/http/user/{id}")
    //@requestparm 으로 쿼리스트링 값을 하나하나 받는것 보다 객체를 보내면 한번에 매핑된다.
    //spring이 매핑되도록 도와준다.
    //웹 브라우저는 user라는 object를 이해 할 수가 없다 그래서 json으로 변경해야한다
    public MUser getTest(@PathVariable String id){

        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
    }
    @PostMapping("http/mkuser")
    public String insertBoard(MUser user){
        System.out.println(user);
        user.setRole(Role.USER);
        userRepository.save(user);
       return "make user success!!" ;
    }

    @PostMapping("/http/post")
    //post는 body에 데이터 전송한다.
    //body 타입에서 전송하는
    public String postTest(@RequestBody MUser user){
        System.out.println(user);


        return "post요청";
    }
    @PutMapping("/http/put")
    public String putTest(@RequestBody MUser user){


        return "put요청";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(@RequestBody HashMap<String, String> map){

        return "delete요청";
    }
}
