package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.model.Role;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import javax.servlet.http.HttpSession;
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

    public MUser mkuser(MUser user){
        MUser already = repository.findById(user.getUserid()).orElse(null);

        if(already == null){
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);
        user.setRole(Role.USER);
        MUser newUser = repository.save(user);
        return newUser;}
        else {
            return null;
        }
    }

    public MUser login(){
    return null;
    }


}
