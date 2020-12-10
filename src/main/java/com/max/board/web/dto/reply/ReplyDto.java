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
    private Long post_id;

    private Long replyId;
    private String replyAuthor;
    private String replyContent;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ReplyDto(Posts posts, String replyAuthor, String replyContent) {
        this.posts        = posts;
        this.replyAuthor  = replyAuthor;
        this.replyContent = replyContent;
    }

    public Reply toEntity() {
        return Reply.builder().posts(posts).replyAuthor(replyAuthor).replyContent(replyContent).build();
    }

    public ReplyDto(Reply entity) {
        this.replyId      = entity.getReplyId();
        this.post_id      = entity.getPosts().getId();
        this.replyAuthor  = entity.getReplyAuthor();
        this.replyContent = entity.getReplyContent();
        this.createDate   = entity.getCreateDate();
        this.modifiedDate = entity.getModifiedDate();
    }

    @JsonProperty("post_id")
    private void unpackNested(Long post_id) {
        this.posts = new Posts();
        posts.setId(post_id);
    }

}
