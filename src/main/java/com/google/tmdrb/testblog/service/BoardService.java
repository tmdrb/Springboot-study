package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.model.Board;
import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.repository.BoardRepository;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.aspectj.apache.bcel.generic.MULTIANEWARRAY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    public List<Board> getAll(){

        return boardRepository.findAll();
    }

    public Board postBlog(String userid,Board board){
        MUser user = userRepository.findById(userid).orElseThrow(()-> new IllegalArgumentException());
        board.setUser(user);
        return boardRepository.save(board);
    }
}
