package com.google.tmdrb.testblog.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.tmdrb.testblog.config.auth.PrincipalDetail;
import com.google.tmdrb.testblog.model.KakaoDTO;
import com.google.tmdrb.testblog.model.KakaoProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;


@Service
public class KakaoOauth extends DefaultOAuth2UserService {


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getName());
        System.out.println(oAuth2User.getAttributes());


        return super.loadUser(userRequest); // 이 때 session에 저장
    }
    //kakao login을 통해 인가받은 코드로 token 생성
    public KakaoDTO getKakaoCode(String code){
        KakaoDTO kakaotoken = null;

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
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaotoken;
    }

    //kakao에서 받은 token으로 kakao server에서 user 정보 가져오기
    public KakaoProfile mkkakao(KakaoDTO kakaotoken){

    KakaoProfile kakaoProfile = new KakaoProfile();
    // java에서 rest api 호출 방법
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Authorization","Bearer "+ kakaotoken.getAccess_token());

        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
    MultiValueMap<String,String> params = new LinkedMultiValueMap<>();



    HttpEntity<MultiValueMap<String,String>> kakaotokenrequest = new HttpEntity<>(headers);

    ResponseEntity<String> response1 = restTemplate.exchange("https://kapi.kakao.com/v2/user/me"
            , HttpMethod.POST,kakaotokenrequest,String.class);
    ObjectMapper objectMapper1 = new ObjectMapper();

    try {
        kakaoProfile = objectMapper1.readValue(response1.getBody(),KakaoProfile.class);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }


        return kakaoProfile;
    }


}
