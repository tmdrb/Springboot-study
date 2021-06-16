package com.google.tmdrb.testblog.config;

import com.google.tmdrb.testblog.service.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean //ioc로 등록해서 스프링 컨테이너가 관리할수 있게
    public BCryptPasswordEncoder encodePWD(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/auth/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/auth/loginProc") // spring security 가 로그인을 가로채서 대신 해준다.
                    .defaultSuccessUrl("/users")
                    .permitAll()
                .and()
                    .logout()
                .and()
                    .csrf()
                    .disable();

    }
}
