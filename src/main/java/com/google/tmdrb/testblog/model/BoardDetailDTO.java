package com.google.tmdrb.testblog.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@Builder
public class BoardDetailDTO {

    private  int id;
    private String title;
    private String content;
    private int count;
    private String userid;
    private Timestamp timestamp;
    private ArrayList<SecurityReplyDTO> replys;

}
