package com.AnonCoBackend.domain.topics.repository;

import com.AnonCoBackend.domain.topics.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitle(String title);
}
