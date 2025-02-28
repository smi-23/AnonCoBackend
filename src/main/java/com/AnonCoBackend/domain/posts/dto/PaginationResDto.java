package com.AnonCoBackend.domain.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaginationResDto<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int size;

    public static <T> PaginationResDto<T> of(List<T> content, int totalPages, long totalElements, int currentPage, int size) {
        return PaginationResDto.<T>builder()
                .content(content)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .currentPage(currentPage)
                .size(size)
                .build();
    }
}
