package com.google.tmdrb.testblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.tmdrb.testblog.model.KakaoDTO;
import com.google.tmdrb.testblog.model.KakaoProfile;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class LoginController {

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code){
        KakaoDTO kakaotoken = null;
        KakaoProfile kakaoProfile = null;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        params.add("grant_type","authorization_code");
        params.add("client_id","a8b117c6a83d4c470135d900ad8f48a7");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code",code);
        params.add("client_secret", "1jIerDMo725WOiIf1s3pOWoz84ejrlmD");

        HttpEntity<MultiValueMap<String,String>> kakaotokenrequest = new HttpEntity<>(params,headers);

        ResponseEntity<String> response = restTemplate.exchange("https://kauth.kakao.com/oauth/token"
                , HttpMethod.POST,kakaotokenrequest,String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            kakaotoken = objectMapper.readValue(response.getBody(),KakaoDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        RestTemplate restTemplate1 = new RestTemplate();

        HttpHeaders headers1 = new org.springframework.http.HttpHeaders();
        headers1.add("Authorization","Bearer "+ kakaotoken.getAccess_token());

        headers1.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String,String> params1 = new LinkedMultiValueMap<>();



        HttpEntity<MultiValueMap<String,String>> kakaotokenrequest1 = new HttpEntity<>(headers1);

        ResponseEntity<String> response1 = restTemplate.exchange("https://kapi.kakao.com/v2/user/me"
                , HttpMethod.POST,kakaotokenrequest1,String.class);
        ObjectMapper objectMapper1 = new ObjectMapper();


        try {
            kakaoProfile = objectMapper1.readValue(response1.getBody(),KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoProfile.toString();
    }

}
