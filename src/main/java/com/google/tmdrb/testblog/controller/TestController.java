package com.google.tmdrb.testblog.controller;


import com.google.tmdrb.testblog.model.Board;
import com.google.tmdrb.testblog.model.MUser;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController

public class TestController {



    @GetMapping("/http/get")
    //@requestparm 으로 쿼리스트링 값을 하나하나 받는것 보다 객체를 보내면 한번에 매핑된다.
    //spring이 매핑되도록 도와준다.
    public String getTest(@RequestParam MUser user){
        System.out.println(user);
        return "get요청";
    }
    @PostMapping("http/post/board")
    public String insertBoard(@RequestBody Board board){
       return "insert board success" ;
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
