package com.google.tmdrb.testblog.config;

import com.google.tmdrb.testblog.service.KakaoOauth;
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

    @Autowired
    private KakaoOauth kakaoOauth;

    @Bean //ioc로 등록해서 스프링 컨테이너가 관리할수 있게
    public BCryptPasswordEncoder encodePWD(){

        return new BCryptPasswordEncoder();
    }
    //spring security 에서는 userid 가 db에서 있는지 확인하고
    //password는 configure 에서 알아서 확인해준다.
    //어떤방식으로 인코딩 했는지 알려주어야 한다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/mkuser","/board/main","/auth/kakao/callback")
                    .permitAll()
                    .antMatchers("/auth/**")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()


                .and()
                    .formLogin()
                    .loginProcessingUrl("/auth/dologin") // spring security 가 로그인을 가로채서 대신 해준다
                    .defaultSuccessUrl("/board/main",true)
                    .failureUrl("/loginfail")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/dologout")
                    .logoutSuccessUrl("/loginpage")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(kakaoOauth)
                .and()
                .defaultSuccessUrl("/board/main",false)
                .and()
                    .csrf()
                    .disable();

    }
}
