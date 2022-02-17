package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.config.auth.PrincipalDetail;
import com.google.tmdrb.testblog.model.Board;
import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.model.Reply;
import com.google.tmdrb.testblog.repository.BoardRepository;
import com.google.tmdrb.testblog.repository.ReplyRepository;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public String postReply(int board_id, Reply reply , PrincipalDetail principalDetail){

        if(principalDetail.isEnabled()){
            MUser user = userRepository.findById(principalDetail.getUsername()).orElseThrow();
            Board board = boardRepository.findById(board_id).orElseThrow();
            reply.setUser(user);
            reply.setBoard(board);

            replyRepository.save(reply);
            return "success";
        }
        return "fail";
    }

    public String deleteReply(int reply_id,PrincipalDetail principalDetail){
        Reply reply = replyRepository.findById(reply_id).orElseThrow();
        if(principalDetail.getUsername().equals(reply.getUser().getUserid())){

            replyRepository.delete(reply);
            return "success";
        }
        return "fail";
    }
    @Transactional
    public String updateReply(int reply_id, Reply reply, PrincipalDetail principalDetail){

        if(principalDetail.getUsername().equals(reply.getUser().getUserid())){
            Reply old = replyRepository.findById(reply_id).orElseThrow();
            old.setContent(reply.getContent());
            return "success";
        }
        return "fail";
    }
}
