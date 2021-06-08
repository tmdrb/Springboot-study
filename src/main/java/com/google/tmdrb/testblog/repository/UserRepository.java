package com.google.tmdrb.testblog.repository;

import com.google.tmdrb.testblog.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

//자동으로 bean으로 등록
//@Repository 생략가능
public interface UserRepository extends JpaRepository<MUser, String> {


}
