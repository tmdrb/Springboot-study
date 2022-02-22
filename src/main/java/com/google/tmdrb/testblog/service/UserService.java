package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.config.auth.PrincipalDetail;
import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.model.Role;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Service
public class UserService {


    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EntityManager em;

    @Transactional
    public MUser update(String userid,MUser user){
        MUser snapshot = em.find(MUser.class,userid);
        snapshot.setUsername(user.getUsername());
        snapshot.setPassword(user.getPassword());
        snapshot.setEmail(user.getEmail());

        return snapshot;
    }

    public MUser haskakaouser(String userid){
        MUser user = repository.findById(userid).orElseGet(()->{
            return null;
        });

        return user;
    }

    public String mkuser(MUser user){
        MUser already = repository.findById(user.getUserid()).orElse(null);

        if(already == null){
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);
        user.setRole(Role.USER);
        MUser newUser = repository.save(user);
        return user.getUserid()+"님 회원가입 완료 됬습니다.";
        }
        else {
            return "이미 존재한는 id 입니다. 중복확인 바랍니다.";
        }
    }

    public String delUser(PrincipalDetail principalDetail){
        repository.deleteById(principalDetail.getUsername());
        return "정상적으로 삭제";
    }

}
