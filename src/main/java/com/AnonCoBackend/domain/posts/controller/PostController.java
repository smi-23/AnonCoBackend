package com.AnonCoBackend.domain.posts.controller;

import com.AnonCoBackend.domain.posts.dto.PostReqDto;
import com.AnonCoBackend.domain.posts.service.PostService;
import com.AnonCoBackend.domain.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<Message> createPost(@RequestBody PostReqDto reqDto) {
        return postService.createPost(reqDto);
    }
}
