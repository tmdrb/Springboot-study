package com.google.tmdrb.testblog.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class Board {

    private String title;

    private String content;

    private int count;

    private List<Reply> replies;
}
