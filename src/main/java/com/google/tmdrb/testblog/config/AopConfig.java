package com.google.tmdrb.testblog.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {
    private static final Logger logger = LogManager.getLogger(AopConfig.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void GetMapping(){ }

    @Around("GetMapping()")
    public void KakaoLogin(){
        logger.info("login 시작");
        logger.info("로그인 끝");
    }

}
