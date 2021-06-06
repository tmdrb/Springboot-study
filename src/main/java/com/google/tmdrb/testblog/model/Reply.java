package com.google.tmdrb.testblog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Data
@Getter
@Setter
public class Reply {

    private String id;
    private String content;

    private List<Reply> replies;


    private Timestamp timestamp;
}
