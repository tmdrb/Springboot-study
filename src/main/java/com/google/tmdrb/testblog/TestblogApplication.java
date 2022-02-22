package com.google.tmdrb.testblog;

import com.google.tmdrb.testblog.controller.BoardController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy //AOP 사용
public class TestblogApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestblogApplication.class, args);
	}


}
