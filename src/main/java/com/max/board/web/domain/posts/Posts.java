package com.max.board.web.domain.posts;

import com.max.board.web.domain.BaseTimeEntity;
import com.max.board.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가, public Posts() {} 와 같은 효과
@Entity // 테이블과 링크될 클래스임을 명시
public class Posts extends BaseTimeEntity { // 실제 DB의 테이블과 매칭될 클래스

    @Id // 해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 테이블의 컬럼값.기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용. ex) VARCHAR(255) -> VARCHAR(500)
    @Column(length = 100, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;
    private String content;

    // 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(User user, String title, String content, String author){
        this.user    = user;
        this.title   = title;
        this.content = content;
        this.author  = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", user=" + user +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
