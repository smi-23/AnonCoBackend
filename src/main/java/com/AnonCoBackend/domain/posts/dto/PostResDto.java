package com.AnonCoBackend.domain.posts.dto;

import lombok.*;

@Getter
@Builder
public class PostResDto {
    private String nickName;
    private String title;
    private String content;
}
