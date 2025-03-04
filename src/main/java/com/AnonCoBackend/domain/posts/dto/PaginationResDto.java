package com.AnonCoBackend.domain.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaginationResDto { // 리팩토링 할 때 제네릭으로 할 지 고민 해야 함
    private String categoryTitle;
    private List<PostResDto> postList;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int size;

    public static PaginationResDto of(String category, List<PostResDto> postList, int totalPages, long totalElements, int currentPage, int size) {
        return PaginationResDto.builder()
                .categoryTitle(category)
                .postList(postList)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .currentPage(currentPage)
                .size(size)
                .build();
    }
}
