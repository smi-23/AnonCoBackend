package com.AnonCoBackend.domain.topics.controller;

import com.AnonCoBackend.domain.topics.dto.TopicReqDto;
import com.AnonCoBackend.domain.topics.service.TopicService;
import com.AnonCoBackend.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @PostMapping("")
    public ResponseEntity<Message> createTopic (@RequestBody TopicReqDto reqDto) {
        topicService.createTopic(reqDto);
        return new ResponseEntity<>(new Message(reqDto.getTitle() + " 토픽이 생성되었습니다.", null), HttpStatus.CREATED);
    }
}
