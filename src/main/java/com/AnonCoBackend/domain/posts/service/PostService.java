package com.AnonCoBackend.domain.posts.service;

import com.AnonCoBackend.domain.posts.dto.PostReqDto;
import com.AnonCoBackend.domain.posts.dto.PostResDto;
import com.AnonCoBackend.domain.posts.entity.Post;
import com.AnonCoBackend.domain.posts.repository.PostRepository;
import com.AnonCoBackend.domain.utils.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public ResponseEntity<Message> createPost(PostReqDto reqDto) {
        Post post = postRepository.save(Post.from(reqDto));
        PostResDto resDto = PostResDto.builder()
                .nickName(post.getNickName())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
        log.info("{}번 게시글이 생성되었습니다.", post.getId());
        return new ResponseEntity<>(new Message(post.getId() + "번 게시글이 생성되었습니다.", resDto), HttpStatus.CREATED);
    }

}
