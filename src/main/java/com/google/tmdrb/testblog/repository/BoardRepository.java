package com.google.tmdrb.testblog.repository;

import com.google.tmdrb.testblog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// jpa를 사용해서 repository로 메소드를 이용해서 간단하게 사용가능
public interface BoardRepository extends JpaRepository<Board,Integer> {
}
