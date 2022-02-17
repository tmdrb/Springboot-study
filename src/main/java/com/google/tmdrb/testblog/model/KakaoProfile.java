package com.google.tmdrb.testblog.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Properties;
@Data
public class KakaoProfile {

    public Integer id;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;

    @Data
    @NoArgsConstructor
    static class KakaoAccount {

        public Boolean profile_needs_agreement;
        public Profile profile;
        public Boolean has_email;
        public Boolean email_needs_agreement;
        public Boolean is_email_valid;
        public Boolean is_email_verified;
        public String email;

    }

    @Data
    @NoArgsConstructor
    static class Profile {

        public String nickname;
        public String profile_image_url;
        public String thumbnail_image_url;
        public Boolean is_default_image;
    }

    @Data
    @NoArgsConstructor
    static class Properties {

        public String nickname;
        public String profile_image;
        public String thumbnail_image;
    }
}