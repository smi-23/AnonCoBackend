package com.AnonCoBackend.domain.posts.repository;

import com.AnonCoBackend.domain.posts.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByCategory_Title(String title, Pageable pageable);
}
