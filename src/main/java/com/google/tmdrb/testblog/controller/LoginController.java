package com.google.tmdrb.testblog.controller;


import com.google.tmdrb.testblog.model.KakaoProfile;
import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.service.KakaoOauth;
import com.google.tmdrb.testblog.service.PrincipalDetailService;
import com.google.tmdrb.testblog.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    KakaoOauth kakaoOauthservice;

    @Autowired
    PrincipalDetailService principalDetailService;

    @Autowired
    UserService userService;

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code){

        KakaoProfile kakaoProfile = kakaoOauthservice.mkkakao(kakaoOauthservice.getKakaoCode(code));
        MUser user = null;
        if((user = userService.haskakaouser(kakaoProfile.getId().toString())) != null){

        }
        else{
            user = MUser.builder()
                    .email(kakaoProfile.getKakao_account().getEmail())
                    .username(kakaoProfile.getProperties().getNickname())
                    .userid(kakaoProfile.getId().toString())
                    .password(kakaoProfile.getId()+"tmdrb")
                    .build();

            userService.mkuser(user);
        }

        //OAUTH를 이용한 로그인 시에는 UsernamePasswordAuthenticationFilter를 사용하지 않아서 인증되지 않은채로 다음 필터로 넘어간다
        //OAuth2ClientAuthenticationProcessingFilter에서 인증해주면 된다.

        //usernametoken을 만들어서 authentication manager에 넘길때 password값을 인코딩 전의 값을 넣는다.
        //패스워드는 config에 작성해 놓은 인코딩방식으로 자동으로 인코딩 시켜준다
        UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(user.getUserid(),kakaoProfile.getId()+"tmdrb");

        try {
            Authentication authentication = authenticationManager.authenticate(t);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return user.getUsername() +"님 반갑습니다.";

    }



}
