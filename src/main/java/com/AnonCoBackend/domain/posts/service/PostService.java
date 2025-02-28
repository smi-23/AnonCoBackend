package com.AnonCoBackend.domain.posts.service;

import com.AnonCoBackend.domain.posts.dto.PaginationResDto;
import com.AnonCoBackend.domain.posts.dto.PostReqDto;
import com.AnonCoBackend.domain.posts.dto.PostResDto;
import com.AnonCoBackend.domain.posts.entity.Post;
import com.AnonCoBackend.domain.posts.repository.PostRepository;
import com.AnonCoBackend.domain.categories.entity.Category;
import com.AnonCoBackend.domain.categories.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public PostResDto createPost(PostReqDto reqDto, String categoryTitle) {
        Category category = categoryRepository.findByTitle(categoryTitle).orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        Post post = postRepository.save(Post.from(reqDto, category));
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
    public PaginationResDto getPostByCategory(String categoryTitle, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Post> postPageByCategory = postRepository.findByCategory_Title(categoryTitle, pageable);
        if (postPageByCategory.isEmpty()) {
            throw new IllegalArgumentException("카테고리 " + categoryTitle + " 에 해당하는 게시글이 존재하지 않습니다.");
        }
        List<PostResDto> resDtoList = postPageByCategory.stream().map(PostResDto::from).toList();
        log.info("카테고리 {} 의 전체 게시글 조회 - 전체 게시글 수: {}, 페이지 게시글 수: {}, 현재 페이지: {}",
                categoryTitle,
                postPageByCategory.getTotalElements(),
                postPageByCategory.getNumberOfElements(),
                postPageByCategory.getNumber() + 1);
        return PaginationResDto.of(
                resDtoList,
                postPageByCategory.getTotalPages(),
                postPageByCategory.getTotalElements(),
                postPageByCategory.getNumber() + 1,
                size);
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
