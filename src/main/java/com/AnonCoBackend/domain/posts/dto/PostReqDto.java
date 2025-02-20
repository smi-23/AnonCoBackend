package com.AnonCoBackend.domain.posts.dto;

import lombok.Getter;

@Getter
public class PostReqDto {
    private String nickName;
    private String password;
    private String title;
    private String content;
    private String topic;
}
