package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.config.auth.PrincipalDetail;
import com.google.tmdrb.testblog.model.*;
import com.google.tmdrb.testblog.repository.BoardRepository;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.aspectj.apache.bcel.generic.MULTIANEWARRAY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    public Board readBoard(int board_id){
        return boardRepository.findById(board_id).orElseThrow(()->new IllegalArgumentException());
    }

    //db에 있는 board를 list로 반환
    @Transactional
    public String updateBoard(int board_id, Board new_board, PrincipalDetail principalDetail){
        Board board = boardRepository.findById(board_id).orElseThrow(()->new IllegalArgumentException());
        if(principalDetail.getUsername().equals(board.getUser().getUserid())){
            board.setContent(new_board.getContent());
            board.setTitle(new_board.getTitle());
            board.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return "success";
        }
        else
            return "fail";

    }
    @Transactional
    public String deleteBoard(int board_id,PrincipalDetail principalDetail){
        Board board = boardRepository.findById(board_id).orElseThrow(()->new IllegalArgumentException());
        if(principalDetail.getUsername().equals(board.getUser().getUserid())){
            boardRepository.delete(board);
        return "success";
        }
        else
            return "fail";
    }

    public String postBlog(Board board,String username){

        MUser user = userRepository.findById(username).orElseThrow(()-> new IllegalArgumentException());

        board.setUser(user);
        boardRepository.save(board);
        return "success";

    }
    @Transactional
    public Optional<BoardDetailDTO> readBoardDetail(int board_id){
        Board board = boardRepository.findById(board_id).orElseThrow(()->new IllegalArgumentException());
        board.setCount(board.getCount()+1);
        List<SecurityReplyDTO> securityReplyDTOs = board.getReply().stream().map(reply -> SecurityReplyDTO.builder()
                .id(reply.getReid())
                .userid(reply.getUser().getUserid())
                .content(reply.getContent())
                .timestamp(reply.getTimestamp())
                .depth(reply.getDepth())
                .parent(reply.getParent()).build()).collect(Collectors.toList());

        BoardDetailDTO boardDetailDTO = BoardDetailDTO.builder()
                .content(board.getContent())
                .title(board.getTitle())
                .userid(board.getUser().getUserid())
                .count(board.getCount())
                .id(board_id)
                .timestamp(board.getTimestamp())
                .replys((ArrayList<SecurityReplyDTO>) securityReplyDTOs)
                .build();

        Optional<BoardDetailDTO> optionalBoardDetailDTO = Optional.of(boardDetailDTO);
        return optionalBoardDetailDTO;
    }
    //db에 있는 board를 page로 반환
    public Page<SecurityBoardDTO> getBoardPage(Pageable pageable){
        Page<Board> boardPage = boardRepository.findAll(pageable);
        Page<SecurityBoardDTO> securityBoardPage = boardPage.map(board -> SecurityBoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .count(board.getCount())
                .userid(board.getUser().getUserid()).build());
        return securityBoardPage;
    }
}
