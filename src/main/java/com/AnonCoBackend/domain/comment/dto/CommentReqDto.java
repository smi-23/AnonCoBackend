package com.AnonCoBackend.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentReqDto {
    private String nickName;
    private String password;
    private String content;
}
