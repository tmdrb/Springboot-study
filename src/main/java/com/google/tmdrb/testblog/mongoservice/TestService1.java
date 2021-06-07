package com.google.tmdrb.testblog.mongoservice;

import org.springframework.stereotype.Service;

//mongorepository interface 상속받아서 구현한 서비스
@Service
public class TestService1  {

   /* @Autowired
    UserRepo userRepo;

    @Autowired
    MongoTemplate mongoTemplate;

    public MUser insert(MUser user){

        user.setTime(LocalDateTime.now());
        return userRepo.insert(user);
    }
    public void insertBoard(MUser user){

        System.out.println(user);

    }
    public void update(MUser user){

        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(user.get_id()).and("password").is(user.getPassword()));
        Update update = new Update();
        update.set("password","0000");

        System.out.println(mongoTemplate.updateFirst(query,update,"users"));

    }*/

}
