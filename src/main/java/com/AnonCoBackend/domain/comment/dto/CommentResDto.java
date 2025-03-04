package com.AnonCoBackend.domain.comment.dto;

import com.AnonCoBackend.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResDto {
    private Long id;
    private String nickName;
    private String content;
    private LocalDateTime createdAt;

    public static CommentResDto from(Comment comment) {
        return CommentResDto.builder()
                .id(comment.getId())
                .nickName(comment.getNickName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
