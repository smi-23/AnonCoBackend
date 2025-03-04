package com.AnonCoBackend.domain.posts.dto;

import com.AnonCoBackend.domain.posts.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResDto {
    private Long id;
    private String nickName;
    private String title;
    private String content;
    private String categoryTitle;
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResDto from(Post post) {
        return PostResDto.builder()
                .id(post.getId())
                .nickName(post.getNickName())
                .title(post.getTitle())
                .content(post.getContent())
                .categoryTitle(post.getCategory().getTitle())
                .commentCount(post.getCommentCount())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
