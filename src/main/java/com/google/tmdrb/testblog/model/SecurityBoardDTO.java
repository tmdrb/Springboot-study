package com.google.tmdrb.testblog.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
@Builder
public class SecurityBoardDTO {

    private  int id;
    private String title;
    private String content;
    private int count;
    private String userid;
}
