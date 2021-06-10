package com.google.tmdrb.testblog.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int reid;

    @ManyToOne
    @JoinColumn(name = "boardid")
    private Board board;

    @ManyToOne
    @JoinColumn(name="userid")
    private MUser user;

    @Column(nullable = false, length = 30)
    private String content;

    private int parent;

    private int depth;

    @CreationTimestamp
    private Timestamp timestamp;
}
