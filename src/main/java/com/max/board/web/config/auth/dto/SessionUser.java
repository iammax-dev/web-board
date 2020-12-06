package com.max.board.web.config.auth.dto;

import com.max.board.web.domain.user.User;
import lombok.Getter;

@Getter
// 인증된 사용자 정보만 필요하기때문에 필수정보인 이름, 이메일, 사진만 필드로 선언
public class SessionUser {

    private Long id;
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.id      = user.getId();
        this.name    = user.getName();
        this.email   = user.getEmail();
        this.picture = user.getPicture();
    }
}
