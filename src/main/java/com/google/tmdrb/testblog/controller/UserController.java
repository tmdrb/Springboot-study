package com.google.tmdrb.testblog.controller;

import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.model.Role;
import com.google.tmdrb.testblog.repository.UserRepository;

import com.google.tmdrb.testblog.service.UserService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class UserController {

    @Autowired //의존성 주입
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/finduser/{id}")
    //@requestparm 으로 쿼리스트링 값을 하나하나 받는것 보다 객체를 보내면 한번에 매핑된다.
    //spring이 매핑되도록 도와준다.
    //웹 브라우저는 user라는 object를 이해 할 수가 없다 그래서 json으로 변경해야한다
    public MUser getUser(@PathVariable String id){
        MUser user =userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        return user;
    }
    @PostMapping("/mkuser")
    public String makeUser(@RequestBody MUser user){
        System.out.println(user);
        user.setRole(Role.USER);
        userRepository.save(user);
        return "make user success!!" ;
    }

    @DeleteMapping("/deleteuser/{userid}")
    public String deleteUser(@PathVariable String userid){

        userRepository.deleteById(userid);
        return "delete complete!!";
    }

    @PutMapping(value = "/updateuser/{userid}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public MUser updateUser(@PathVariable String userid,@RequestBody MUser user){


        return userService.update(userid,user);
    }

    @PostMapping("/login")
    public String loginUser(String userid,String password){
        if(userService.login(userid,password))
            return "login sucess";
        else
            return "login fail";
    }

}
