package com.google.tmdrb.testblog.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


import org.hibernate.annotations.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;


//table화 user 클래스가 mongo 에 자동으로 테이블이 생성된다





 //자동으로 mysql에 table 이 생성된다
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicUpdate
//@DynamicInsert // null 값인 column 은 제거 시켜준다
@Entity //entitymanager 에 인식할수 있다 @id로 구별
public class MUser {


    //@GeneratedValue(strategy=GenerationType.IDENTITY) -> 프로젝트에서 연결된 db의 넘버링 전략을 따라간다.


    @Id
    @Column(nullable = false, length = 20)
    private String userid;

    @Column(nullable = false, length = 10)
    private String username;
    // 비밀번호를 해쉬로 암호화 하기 위해서 길이를 길게 잡는다.
    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 20)
    private String email;

    //db 는 role type 이 없기 때문에 알려줘야 한다
    @Enumerated(EnumType.STRING)
    private Role role;


    @CreationTimestamp //시간이 자동으로 입력된다.
    private Timestamp time;


}
