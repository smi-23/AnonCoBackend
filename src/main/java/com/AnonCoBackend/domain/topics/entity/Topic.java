package com.AnonCoBackend.domain.topics.entity;

import com.AnonCoBackend.domain.topics.dto.TopicReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @Column(nullable = false)
    String title;

    public static Topic from (TopicReqDto reqDto) {
        return Topic.builder()
                .title(reqDto.getTitle())
                .build();
    }
}
