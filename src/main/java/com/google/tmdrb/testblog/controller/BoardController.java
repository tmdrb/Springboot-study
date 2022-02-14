package com.google.tmdrb.testblog.controller;


import com.google.tmdrb.testblog.config.auth.PrincipalDetail;
import com.google.tmdrb.testblog.model.*;

import com.google.tmdrb.testblog.repository.BoardRepository;
import com.google.tmdrb.testblog.repository.ReplyRepository;
import com.google.tmdrb.testblog.repository.UserRepository;
import com.google.tmdrb.testblog.service.BoardService;
import com.google.tmdrb.testblog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ReplyService replyService;

    @GetMapping("/main")
    public Page<SecurityBoardDTO> pageList(@PageableDefault(size=10,sort="timestamp",direction= Sort.Direction.DESC) Pageable pageable){

        Page<SecurityBoardDTO> securityBoardPage = boardService.getBoardPage(pageable);

        return securityBoardPage;
    }

    @PostMapping("/postboard")
    //post는 body에 데이터 전송한다.
    //body 타입에서 전송하는
    public String postBoard(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principalDetail){
        String output = "";
        if(principalDetail.isEnabled()){
            output = boardService.postBlog(board,principalDetail.getUsername());
            if(output == "success"){
                return "post complete";
            }
            else
                return "post fail";
        }
        else
            return "login please";
    }
    @PostMapping("/{board_id}/postreply")
        public String postReply(@PathVariable("board_id") int board_id,@RequestBody Reply reply,@AuthenticationPrincipal PrincipalDetail principalDetail){

        String output = replyService.postReply(board_id,reply,principalDetail);
        if(output == "success")
            return "complete";
        else
            return "fail";
    }

    @PutMapping("/putboard/{board_id}")
    public String putBoard(@PathVariable("board_id") int board_id,@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principalDetail){

        String output = boardService.updateBoard(board_id,board,principalDetail);
        if(output == "success")
            return "update success";
        return "update fail";
    }
    @DeleteMapping("/deleteboard/{board_id}")
    public String deleteBoard(@PathVariable("board_id") int board_id,@AuthenticationPrincipal PrincipalDetail principalDetail){

        String output = boardService.deleteBoard(board_id,principalDetail);
        if(output == "success")
            return "delete success";
        return "delete fail";
    }

    @GetMapping("/{board_id}")
    public BoardDetailDTO readBoard(@PathVariable("board_id") int board_id){
        BoardDetailDTO boardDetailDTO = boardService.readBoardDetail(board_id).orElseThrow(()->new IllegalArgumentException());
        return boardDetailDTO;
    }


}
