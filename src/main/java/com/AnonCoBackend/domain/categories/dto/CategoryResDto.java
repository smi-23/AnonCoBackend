package com.AnonCoBackend.domain.categories.dto;

import com.AnonCoBackend.domain.categories.entity.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResDto {
    private Long id;
    private String title;
    private String url;

    public static CategoryResDto from (Category category) {
        return CategoryResDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .url(category.getUrl())
                .build();
    }
}
