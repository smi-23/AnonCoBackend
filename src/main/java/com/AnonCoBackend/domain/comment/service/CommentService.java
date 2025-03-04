package com.AnonCoBackend.domain.comment.service;

import com.AnonCoBackend.domain.comment.dto.CommentReqDto;
import com.AnonCoBackend.domain.comment.dto.CommentResDto;
import com.AnonCoBackend.domain.comment.entity.Comment;
import com.AnonCoBackend.domain.comment.repository.CommentRepository;
import com.AnonCoBackend.domain.posts.entity.Post;
import com.AnonCoBackend.domain.posts.repository.PostRepository;
import com.AnonCoBackend.utils.PwEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<CommentResDto> getComment(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        List<Comment> commentList = commentRepository.findByPostId(postId);
        log.info("{}번 게시글의 댓글 조회, 해당 게시글의 총 댓글 수: {}", postId, post.getCommentCount());
        return commentList.stream().map(CommentResDto::from).toList();
    }

    @Transactional
    public CommentResDto createComment(Long postId, CommentReqDto reqDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        Comment comment = commentRepository.save(Comment.from(post, reqDto));
        post.increaseCommentCount();
        postRepository.save(post);
        log.info("{}번 게시글에 댓글 생성, 해당 게시글의 총 댓글 수: {}", postId, post.getCommentCount());
        return CommentResDto.from(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, String password) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
        if (!PwEncoder.encoder.matches(password, comment.getPassword())) {
            throw new IllegalArgumentException(commentId + "번 포스트의 비밀번호와 일치하지 않습니다.");
        }
        Post post = comment.getPost();
        post.decreaseCommentCount();
        postRepository.save(post);
        log.info("{}번 댓글 삭제", commentId);
        commentRepository.delete(comment);
    }
}
