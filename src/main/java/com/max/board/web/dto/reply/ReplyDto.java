package com.max.board.web.dto.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.max.board.web.domain.posts.Posts;
import com.max.board.web.domain.reply.Reply;
import com.max.board.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class ReplyDto {

    private Posts posts;
    private Long posts_Id;
    private User user;
    private Long user_Id;

    private Long replyId;
    private String replyAuthor;
    private String replyContent;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public Reply toEntity() {
        return Reply.builder().posts(posts).user(user).replyAuthor(replyAuthor).replyContent(replyContent).build();
    }

    @JsonProperty("post_id")
    private void unpackNested(Long post_id) {
        this.posts = new Posts();
        posts.setId(post_id);
    }

    @JsonProperty("user_id")
    private void unpackNested2(Long user_id) {
        this.user  = new User();
        user.setId(user_id);
    }

    @Override
    public String toString() {
        return "ReplyDto{" +
                "posts=" + posts +
                ", user=" + user +
                ", replyId=" + replyId +
                ", replyAuthor='" + replyAuthor + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
