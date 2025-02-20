package com.AnonCoBackend.domain.topics.service;

import com.AnonCoBackend.domain.topics.dto.TopicReqDto;
import com.AnonCoBackend.domain.topics.entity.Topic;
import com.AnonCoBackend.domain.topics.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    @Transactional
    public void createTopic (TopicReqDto reqDto) {
        Optional<Topic> optionalTopic = topicRepository.findByTitle(reqDto.getTitle());
        if(optionalTopic.isPresent()){
            throw new IllegalArgumentException("해당 토픽이 이미 존재합니다.");
        }
        topicRepository.save(Topic.from(reqDto));
        log.info("{} 토픽 생성", reqDto.getTitle());
    }
}
