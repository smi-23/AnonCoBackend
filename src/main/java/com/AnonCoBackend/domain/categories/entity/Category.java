package com.AnonCoBackend.domain.categories.entity;

import com.AnonCoBackend.domain.categories.dto.CategoryReqDto;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String url;

    public static Category from(CategoryReqDto reqDto) {
        return Category.builder()
                .title(reqDto.getTitle())
                .url(reqDto.getUrl())
                .build();
    }
}
