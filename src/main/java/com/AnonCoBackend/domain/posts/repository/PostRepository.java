package com.AnonCoBackend.domain.posts.repository;

import com.AnonCoBackend.domain.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
