package com.google.tmdrb.testblog.service;
import com.google.tmdrb.testblog.model.MUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public MUser getUser(String _id){
        MUser user = mongoTemplate.findById(_id,MUser.class);
        return Optional.ofNullable(user).orElseThrow(()->new NullPointerException("not found user"));
    }
    public List<MUser> getAll(){
        return mongoTemplate.findAll(MUser.class,"users");

    }


    public void updateUser(MUser user){

        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("username").is(user.getUsername()));
        update.set("password","0000");

        mongoTemplate.updateFirst(query,update,"users");

    }

    public MUser insertUser(MUser body){

        System.out.println("mongodb insert");
        return mongoTemplate.insert(body);
    }
}
