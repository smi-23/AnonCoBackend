package com.AnonCoBackend.domain.comment.controller;

import com.AnonCoBackend.domain.comment.dto.CommentReqDto;
import com.AnonCoBackend.domain.comment.dto.CommentResDto;
import com.AnonCoBackend.domain.comment.service.CommentService;
import com.AnonCoBackend.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<Message> getAllComment (@PathVariable("postId") Long postId) {
        List<CommentResDto> resDtoList = commentService.getComment(postId);
        return new ResponseEntity<>(new Message(postId +"번 게시글의 댓글을 모두 조회합니다.", resDtoList), HttpStatus.OK);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Message> createComment (@PathVariable("postId") Long postId, @RequestBody CommentReqDto reqDto) {
        CommentResDto resDto = commentService.createComment(postId, reqDto);
        return new ResponseEntity<>(new Message(postId +"번 게시글의 댓글을 생성하였습니다.", resDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Message> deleteComment (@PathVariable("commentId") Long commentId, @RequestBody Map<String, String> request) {
        String password = request.get("password");
        commentService.deleteComment(commentId, password);
        return new ResponseEntity<>(new Message(commentId +"번 게시글의 댓글을 삭제하였습니다.", null), HttpStatus.OK);
    }
}
