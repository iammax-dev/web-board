package com.max.board.web.dto;

import com.max.board.web.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {

        this.id           = entity.getId();
        this.title        = entity.getTitle();
        this.author       = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.createDate   = entity.getCreateDate();
    }
}
