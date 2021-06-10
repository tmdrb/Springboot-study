package com.google.tmdrb.testblog.repository;

import com.google.tmdrb.testblog.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//자동으로 bean으로 등록
//@Repository 생략가능
public interface UserRepository extends JpaRepository<MUser, String> {
    @Query(value="select u from MUser u where u.userid =?1 and u.password=?2")
    public MUser findbyidpass(String userid,String password);

}
