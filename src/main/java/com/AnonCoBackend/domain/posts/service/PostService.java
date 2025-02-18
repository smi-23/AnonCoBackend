package com.AnonCoBackend.domain.posts.service;

import com.AnonCoBackend.domain.posts.dto.PostReqDto;
import com.AnonCoBackend.domain.posts.dto.PostResDto;
import com.AnonCoBackend.domain.posts.entity.Post;
import com.AnonCoBackend.domain.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostResDto createPost(PostReqDto reqDto) {
        Post post = postRepository.save(Post.from(reqDto));
        log.info("{}번 게시글 생성", post.getId());
        return PostResDto.from(post);
    }

    @Transactional
    public List<PostResDto> getAllPost() {
        List<Post> postList = postRepository.findAll();
        log.info("전체 게시글 조회 - 게시글 수: {}", postList.size());
        return postList.stream().map(PostResDto::from).toList();
    }

    @Transactional
    public PostResDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "번 게시글이 존재하지 않습니다."));
        log.info("{}번 게시글 조회", id);
        return PostResDto.from(post);
    }

    @Transactional
    public PostResDto updatePost(Long id, PostReqDto reqDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "번 게시글이 존재하지 않습니다."));
        post.updatePost(reqDto);
        postRepository.save(post);
        log.info("{}번 게시글 수정", id);
        return PostResDto.from(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "번 게시글이 존재하지 않습니다."));
        log.info("{}번 게시글 삭제", id);
        postRepository.delete(post);
    }
}
