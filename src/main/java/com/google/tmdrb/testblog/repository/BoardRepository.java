package com.google.tmdrb.testblog.repository;

import com.google.tmdrb.testblog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {
}
