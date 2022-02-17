package com.google.tmdrb.testblog.model;

import lombok.Data;

@Data
public class KakaoDTO {
    private String access_token;
    private String expires_in;
    private String token_type;
    private String refresh_token;
    private String refresh_token_expires_in;
    private String scope;

}
