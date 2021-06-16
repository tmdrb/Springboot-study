package com.google.tmdrb.testblog.config.auth;

import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.service.PrincipalDetailService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetail implements UserDetails {
    private MUser user; //컴퍼지션 (객체를 품고 있는것)

    public PrincipalDetail(MUser user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{return "ROLE_"+user.getRole();});
        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
