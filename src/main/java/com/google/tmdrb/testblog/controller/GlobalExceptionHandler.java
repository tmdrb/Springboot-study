package com.google.tmdrb.testblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 exception이 오면 여기 controller 로 안내한다.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class) // illegal exception 이 발생하면 이함수 실행
    public String handleArgumentException(IllegalArgumentException e){
        return e.toString();
    }

}
