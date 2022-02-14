package com.google.tmdrb.testblog.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class SecurityReplyDTO {

    private  int id;
    private String content;
    private String userid;
    private int depth;
    private int parent;
    private Timestamp timestamp;

}
