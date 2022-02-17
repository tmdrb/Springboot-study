package com.google.tmdrb.testblog.controller;

import com.google.tmdrb.testblog.config.auth.PrincipalDetail;
import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.model.Role;
import com.google.tmdrb.testblog.repository.UserRepository;

import com.google.tmdrb.testblog.service.UserService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired //의존성 주입
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/getall")
    public List<MUser> getUsers(){

        return userRepository.findAll();
    }


    @GetMapping("/user/{id}")
    //@requestparm 으로 쿼리스트링 값을 하나하나 받는것 보다 객체를 보내면 한번에 매핑된다.
    //spring이 매핑되도록 도와준다.
    //웹 브라우저는 user라는 object를 이해 할 수가 없다 그래서 json으로 변경해야한다
    public MUser getUser(@PathVariable String id){
        MUser user =userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        return user;
    }
    @PostMapping("/mkuser")
    public String makeUser(MUser user){
        System.out.println(user);

        String message = userService.mkuser(user);

        return message;
    }


    @DeleteMapping("/auth/deleteuser/{userid}")
    public String deleteUser(@PathVariable String userid,@AuthenticationPrincipal PrincipalDetail principalDetail){
        if(principalDetail != null) {
            userService.delUser(principalDetail);


        }

        return "delete complete!!";
    }

    @PutMapping(value = "/user/{userid}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public MUser updateUser(@PathVariable String userid,@RequestBody MUser user){


        return userService.update(userid,user);
    }



}
