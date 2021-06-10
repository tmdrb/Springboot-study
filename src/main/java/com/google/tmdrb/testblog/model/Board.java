package com.google.tmdrb.testblog.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) // vo 에서 null 값이면 빼버리고 추가한다.
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

    @ManyToOne(fetch = FetchType.EAGER) // Many = board , user = one fetch -> board table 을 불러올때 eager전략을 사용하면 바로 user table 을 조인한다 -> 기본전략
    @JoinColumn(name = "userid") //field 값은 userid로 만들어진다.
    private MUser user; //db는 오브젝트 저장 할 수 없다. 그래서 fk 사용

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //mappeby -> 연관관계의 주인이 아니다 라고 알린다
    // fk를 생성하지 않고 db에 table을 추가 하지 말아라
    //onetomany 는 기본 fetch 전략이 lazy 전략이다 -> reply가 여러건이기 때문에 필요하면 들고오고 안필요하면 안들고 올게 전략
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp timestamp;
}
