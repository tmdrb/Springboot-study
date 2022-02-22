package com.google.tmdrb.testblog.model;


import lombok.*;

import java.util.Properties;

@Data
@NoArgsConstructor
public class KakaoProfile {

    public Integer id;
    public String connected_at;
    public KakaoProperties properties;
    public KakaoAccount kakao_account;

    @Data
    @NoArgsConstructor
    public static class KakaoAccount {

        private Boolean profile_needs_agreement;
        private Profile profile;
        private Boolean has_email;
        private Boolean email_needs_agreement;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
        private String email;

    }

    @Data
    @NoArgsConstructor
    public static class Profile {

        private String nickname;
        private String profile_image_url;
        private String thumbnail_image_url;
        private Boolean is_default_image;
    }

    @Data
    @NoArgsConstructor
    public static class KakaoProperties {

        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

}
