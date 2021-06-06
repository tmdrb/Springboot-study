package com.google.tmdrb.testblog.repository;

import com.google.tmdrb.testblog.model.MUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<MUser, String> {

}
