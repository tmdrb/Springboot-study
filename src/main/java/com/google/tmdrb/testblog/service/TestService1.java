package com.google.tmdrb.testblog.service;

import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService1  {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MongoTemplate mongoTemplate;

    public MUser insert(MUser user){

        return userRepo.insert(user);
    }

    public void update(MUser user){

        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(user.get_id()).and("password").is(user.getPassword()));
        Update update = new Update();
        update.set("password","0000");

        System.out.println(mongoTemplate.updateFirst(query,update,"users"));

    }

}
