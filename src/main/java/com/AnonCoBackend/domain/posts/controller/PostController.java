package com.AnonCoBackend.domain.posts.controller;

import com.AnonCoBackend.domain.posts.dto.PostReqDto;
import com.AnonCoBackend.domain.posts.dto.PostResDto;
import com.AnonCoBackend.domain.posts.service.PostService;
import com.AnonCoBackend.domain.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<Message> createPost(@RequestBody PostReqDto reqDto) {
        PostResDto resDto = postService.createPost(reqDto);
        return new ResponseEntity<>(new Message(resDto.getId() + "번 게시글이 생성되었습니다.", resDto), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Message> getAllPost() {
        List<PostResDto> resDtoList = postService.getAllPost();
        return new ResponseEntity<>(new Message("전체 게시글을 조회합니다.", resDtoList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getPost(@PathVariable Long id) {
        PostResDto resDto = postService.getPost(id);
        return new ResponseEntity<>(new Message(id + "번 게시글을 조회합니다.", resDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Message> updatePost(@PathVariable Long id, @RequestBody PostReqDto reqDto) {
        PostResDto resDto= postService.updatePost(id, reqDto);
        return new ResponseEntity<>(new Message(id + "번 게시글이 수정되었습니다.", resDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(new Message(id + "번 게시글이 삭제되었습니다.", null), HttpStatus.OK);
    }
}
