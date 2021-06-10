package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {

    private final String pattern = "^\\S+[\\w+|^\\-\\\"\\\'\\$\\%\\|]$";
    @Autowired
    private UserRepository repository;

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


    public boolean login(String userid,String password){

        if(userid.matches(pattern) && password.matches(pattern)){
        MUser user = repository.findbyidpass(userid,password);
        System.out.println(user);
        if(user!= null)
            return true;
        else
            return false;
        }
        else return false;
    }
}
