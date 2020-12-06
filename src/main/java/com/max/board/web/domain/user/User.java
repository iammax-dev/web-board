package com.max.board.web.domain.user;

import com.max.board.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동 추가, public Posts() {} 와 같은 효과
@Entity // 테이블과 링크될 클래스임을 명시
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    // JPA로 DB 저장할 때 Enum 값을 어떤 형태로 저장할지 결정 (기본 int 로 된 숫자)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(Long id, String name, String email, String picture, Role role) {

        this.id      = id;
        this.name    = name;
        this.email   = email;
        this.picture = picture;
        this.role    = role;
    }

    public User update(String name, String picture) {

        this.name    = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
