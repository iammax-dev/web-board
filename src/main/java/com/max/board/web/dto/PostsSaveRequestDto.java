package com.max.board.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.max.board.web.domain.posts.Posts;
import com.max.board.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private User user;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(User user, String title, String content, String author) {
        this.user    = user;
        this.title   = title;
        this.content = content;
        this.author  = author;
    }

    public Posts toEntity() {
        return Posts.builder().user(user).title(title).content(content).author(author).build();
    }

    @JsonProperty("user_id")
    private void unpackNested(Long user_id) {
        this.user = new User();
        user.setId(user_id);
    }

}
