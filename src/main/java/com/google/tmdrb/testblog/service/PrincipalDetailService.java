package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.config.auth.PrincipalDetail;
import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//spring security 에서 로그인을 가로채서 로그인을 진행하기 때문에 로그인 서비스를 만든다.
//userdetailsservice 를 implements를 해서 만들어 준다.
@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //userdetailsservice 에서 로그인 서비스를 할 때 userid만 있는지 없는지 찾기만 하면 된다.
    //password는 비교하지 않아도 security 에서 자동으로 비교해준다.
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        MUser principal = userRepository.findById(userid).orElseThrow(()->
        {return new UsernameNotFoundException("해당 사용자를 찾을수 없습니다.");
        });

        return new PrincipalDetail(principal); //securiy session 에 유저정보 저장 Userdetails 타입으로로
    }
}
