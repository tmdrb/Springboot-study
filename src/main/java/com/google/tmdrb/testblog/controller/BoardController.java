package com.google.tmdrb.testblog.controller;


import com.google.tmdrb.testblog.model.Board;
import com.google.tmdrb.testblog.model.MUser;

import com.google.tmdrb.testblog.model.Role;
import com.google.tmdrb.testblog.repository.UserRepository;
import com.google.tmdrb.testblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@RestController
@RequestMapping("/blog")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/getall")
    public List<Board> getAllBoard(){
        return boardService.getAll();
    }


    @PostMapping("/postboard/{userid}")
    //post는 body에 데이터 전송한다.
    //body 타입에서 전송하는
    public String postTest(@PathVariable String userid,@RequestBody Board board){
        if(boardService.postBlog(userid,board) != null){
            return "post complete";
        }
        return "post failed";

    }
    @PutMapping("/blog/putboard/{board_id}")
    public String putBoard(@RequestBody MUser user){


        return "put요청";
    }
    @DeleteMapping("/blog/deleteboard/{board_id}")
    public String deleteBoard(){

        return "delete요청";
    }


}
