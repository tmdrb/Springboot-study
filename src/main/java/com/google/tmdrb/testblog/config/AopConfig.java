package com.google.tmdrb.testblog.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
public class AopConfig {
    private static final Logger logger = LogManager.getLogger(AopConfig.class);



    @Around("within(com.google.tmdrb.testblog..*)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable{

        String params = getRequestParams();

        logger.info("----------> REQUEST : {}({}) = {}", pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(), params);

        Object result = pjp.proceed();
        logger.info("----------> RESPONSE : {}({}) = {} ", pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(), result);
        return result;
    }

    public String getRequestParams(){

        String params ="";

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if(requestAttributes != null){

            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                    .getRequest();

            Map<String,String[]> paramMap = request.getParameterMap();

            if(!paramMap.isEmpty()){
                params = paramMap.toString();
            }

        }
        return params;
    }

}
