package com.google.tmdrb.testblog.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(nullable = false, length = 30)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne // Many = board , user = one
    @JoinColumn(name = "userid") //field 값은 userid로 만들어진다.
    private MUser user; //db는 오브젝트 저장 할 수 없다. 그래서 fk 사용

    @CreationTimestamp
    private Timestamp timestamp;
}
