package com.AnonCoBackend.domain.comment.entity;

import com.AnonCoBackend.domain.comment.dto.CommentReqDto;
import com.AnonCoBackend.domain.posts.entity.Post;
import com.AnonCoBackend.utils.PwEncoder;
import com.AnonCoBackend.utils.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    public static Comment from (Post post, CommentReqDto reqDto){
        return Comment.builder()
                .nickName(reqDto.getNickName())
                .password(PwEncoder.encodePw(reqDto.getPassword()))
                .content(reqDto.getContent())
                .post(post)
                .build();
    }

}
