package com.google.tmdrb.testblog.config.auth;

import com.google.tmdrb.testblog.model.MUser;
import com.google.tmdrb.testblog.service.PrincipalDetailService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//spring security 에서 로그인을 가로채고 난 뒤 완료가 되면 userdetails 타입의 오브젝트를
//spring security session 저장소에 저장을 한다.
//User type으로 저장 되는것이 아닌 userdetails type으로 저장되기 때문에
//새로운 객체를 만들어야한다.
public class PrincipalDetail implements UserDetails, OAuth2User {
    private MUser user; //컴퍼지션 (객체를 품고 있는것)


    private Map<String,Object> oauth_attributes;
    public PrincipalDetail(MUser user){
        this.user = user;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return oauth_attributes;
    }

    // 인증받은 사용자 리스트 반환
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

    @Override
    public String getName() {
        return "제공자 id";
    }
}
