package com.google.tmdrb.testblog.model;


import lombok.*;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;


//table화 user 클래스가 mongo 에 자동으로 테이블이 생성된다

@Document(collection = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MUser {

    @Id//프라이머리 키설정
    private String _id;
    private String username;
    // 비밀번호를 해쉬로 암호화 하기 위해서 길이를 길게 잡는다.
    private String password;
    private String email;

    private List<Board> boards;



    @Override
    public String toString() {
        return "User{" +
                "id='" + _id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +

                '}';
    }
}
