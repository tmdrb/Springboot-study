package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.config.auth.PrincipalDetail;
import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MUser principal = userRepository.findById(username).orElseThrow(()->
        {return new UsernameNotFoundException("해당 사용자를 찾을수 없습니다.");
        });

        return new PrincipalDetail(principal); //securiy session 에 유저정보 저장 Userdetails 타입으로로
    }
}
