package com.AnonCoBackend.domain.posts.entity;

import com.AnonCoBackend.domain.posts.dto.PostReqDto;
import com.AnonCoBackend.domain.utils.PwEncoder;
import com.AnonCoBackend.domain.utils.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public static Post from(PostReqDto reqDto) {
        return Post.builder()
                .nickName(reqDto.getNickName())
                .password(PwEncoder.encodePw(reqDto.getPassword()))
                .title(reqDto.getTitle())
                .content(reqDto.getContent())
                .build();
    }

    public void updatePost(PostReqDto reqDto) {
        if (reqDto.getTitle() != null) {
            this.title = reqDto.getTitle();
        }
        if (reqDto.getContent() != null) {
            this.content = reqDto.getContent();
        }
    }
}
