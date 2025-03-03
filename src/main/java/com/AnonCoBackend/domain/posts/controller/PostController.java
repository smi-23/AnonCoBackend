package com.AnonCoBackend.domain.posts.controller;

import com.AnonCoBackend.domain.posts.dto.PaginationResDto;
import com.AnonCoBackend.domain.posts.dto.PostReqDto;
import com.AnonCoBackend.domain.posts.dto.PostResDto;
import com.AnonCoBackend.domain.posts.entity.Post;
import com.AnonCoBackend.domain.posts.service.PostService;
import com.AnonCoBackend.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<Message> createPost(@RequestBody PostReqDto reqDto, @RequestParam("categoryTitle") String categoryTitle) {
        PostResDto resDto = postService.createPost(reqDto, categoryTitle);
        return new ResponseEntity<>(new Message(resDto.getId() + "번 게시글이 생성되었습니다.", resDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Message> getAllPost() {
        List<PostResDto> resDtoList = postService.getAllPost();
        return new ResponseEntity<>(new Message("전체 게시글을 조회합니다.", resDtoList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getPost(@PathVariable("id") Long id) {
        PostResDto resDto = postService.getPost(id);
        return new ResponseEntity<>(new Message(id + "번 게시글을 조회합니다.", resDto), HttpStatus.OK);
    }

    // 카테고리별 페이징
    @GetMapping("/category")
    public ResponseEntity<Message> getPostByCategory(@RequestParam("categoryTitle") String categoryTitle,
                                                     @RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        PaginationResDto resDto = postService.getPostByCategory(categoryTitle, page, size);
        return new ResponseEntity<>(new Message(categoryTitle + "에 해당하는 게시글을 모두 조회합니다.", resDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Message> updatePost(@PathVariable("id") Long id, @RequestBody PostReqDto reqDto) {
        PostResDto resDto = postService.updatePost(id, reqDto);
        return new ResponseEntity<>(new Message(id + "번 게시글이 수정되었습니다.", resDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deletePost(@RequestBody Map<String, String> request, @PathVariable("id") Long id) {
        postService.deletePost(request.get("password"), id);
        return new ResponseEntity<>(new Message(id + "번 게시글이 삭제되었습니다.", null), HttpStatus.OK);
    }

    @PostMapping("/{id}/check-password")
    public ResponseEntity<Message> checkPassword(@RequestBody Map<String, String> request, @PathVariable("id") Long id){
        Post post = postService.findPost(id);
        postService.checkPassword(request.get("password"), post);
        return new ResponseEntity<>(new Message("비밀번호가 일치합니다.", null), HttpStatus.OK);
    }
}
