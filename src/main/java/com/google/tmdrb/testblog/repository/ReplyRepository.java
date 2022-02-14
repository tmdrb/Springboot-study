package com.google.tmdrb.testblog.repository;


import com.google.tmdrb.testblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ReplyRepository extends JpaRepository<Reply,Integer> {
}
