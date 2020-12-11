package com.max.board.web.domain.reply;

import com.max.board.web.domain.BaseTimeEntity;
import com.max.board.web.domain.posts.Posts;
import com.max.board.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {

    @Id // 해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @ManyToOne(targetEntity = Posts.class)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts posts;

    @ManyToOne (targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 테이블의 컬럼값.기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용. ex) VARCHAR(255) -> VARCHAR(500)
    @Column(length = 100, nullable = false)
    private String replyAuthor;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String replyContent;

    @Builder
    public Reply(Posts posts, User user, String replyAuthor, String replyContent){
        this.posts        = posts;
        this.user         = user;
        this.replyAuthor  = replyAuthor;
        this.replyContent = replyContent;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", posts=" + posts +
                ", user=" + user +
                ", replyAuthor='" + replyAuthor + '\'' +
                ", replyContent='" + replyContent + '\'' +
                '}';
    }
}
