package com.AnonCoBackend.domain.posts.service;

import com.AnonCoBackend.domain.posts.dto.PaginationResDto;
import com.AnonCoBackend.domain.posts.dto.PostReqDto;
import com.AnonCoBackend.domain.posts.dto.PostResDto;
import com.AnonCoBackend.domain.posts.entity.Post;
import com.AnonCoBackend.domain.posts.repository.PostRepository;
import com.AnonCoBackend.domain.categories.entity.Category;
import com.AnonCoBackend.domain.categories.repository.CategoryRepository;
import com.AnonCoBackend.utils.PwEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResDto createPost(PostReqDto reqDto, String categoryUrl) {
        Category category = categoryRepository.findByUrl(categoryUrl).orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
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
        Post post = findPost(id);
        log.info("{}번 게시글 조회", id);
        return PostResDto.from(post);
    }

    @Transactional
    public PaginationResDto getPostByCategory(String categoryUrl, int page, int size, String sortOrder) {
        Sort sort = sortOrder.equals("asc") ? Sort.by("updatedAt").ascending() : Sort.by("updatedAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Post> postPageByCategory = postRepository.findByCategory_Url(categoryUrl, pageable);

        List<PostResDto> resDtoList = postPageByCategory.stream().map(PostResDto::from).toList();
        log.info("카테고리 {} 의 전체 게시글 조회 - 전체 게시글 수: {}, 페이지 게시글 수: {}, 현재 페이지: {}",
                categoryUrl,
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
        Post post = findPost(id);
        checkPassword(reqDto.getPassword(), post);
        post.updatePost(reqDto);
        postRepository.save(post);
        log.info("{}번 게시글 수정", id);
        return PostResDto.from(post);
    }

    @Transactional
    public void deletePost(String password, Long id) {
        log.info("password: {}  ~~비밀번호가 어떻게 넘어왔을까요~~", password);
        Post post = findPost(id);
        checkPassword(password, post);
        log.info("{}번 게시글 삭제", id);
        postRepository.delete(post);
    }

    public void checkPassword(String password, Post post) {
        if (!PwEncoder.encoder.matches(password, post.getPassword())) {
            log.info("id: {} 게시글의 비밀번호 불일치", post.getId());
            throw new IllegalArgumentException(post.getId() + "번 포스트의 비밀번호와 일치하지 않습니다.");
        }
    }

    public Post findPost(Long id) { // overloading으로 다양한 매개변수를 받을 수 있게할까?
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번 게시글이 존재하지 않습니다."));
    }
}
