package com.google.tmdrb.testblog.model;


import lombok.*;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


//table화 user 클래스가 mongo 에 자동으로 테이블이 생성된다





@Entity //자동으로 mysql에 table 이 생성된다
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MUser {
    enum ROLE{ADMIN,USER,MANAGER}

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

    @ColumnDefault("'USER'")
    @Enumerated(value = EnumType.STRING)
    private ROLE role;

    @CreationTimestamp //시간이 자동으로 입력된다.
    private Timestamp time;


}
