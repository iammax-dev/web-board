package com.max.board.web.dto.post;

import com.max.board.web.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Long user_id;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public PostsResponseDto(Posts entity) {
        this.id           = entity.getId();
        this.title        = entity.getTitle();
        this.content      = entity.getContent();
        this.author       = entity.getAuthor();
        this.user_id      = entity.getUser().getId();
        this.createDate   = entity.getCreateDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
