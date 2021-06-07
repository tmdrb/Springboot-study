package com.google.tmdrb.testblog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int reid;

    @ManyToOne
    @JoinColumn(name = "id")
    private Board board;

    @OneToOne
    @JoinColumn(name="userid")
    private MUser user;

    @Column(nullable = false, length = 30)
    private String content;

    @CreationTimestamp
    private Timestamp timestamp;
}
