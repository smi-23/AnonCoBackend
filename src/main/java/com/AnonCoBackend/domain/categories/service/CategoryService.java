package com.AnonCoBackend.domain.categories.service;

import com.AnonCoBackend.domain.categories.dto.CategoryReqDto;
import com.AnonCoBackend.domain.categories.entity.Category;
import com.AnonCoBackend.domain.categories.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void createCategory(CategoryReqDto reqDto) {
        Optional<Category> optionalTitle = categoryRepository.findByTitle(reqDto.getTitle());
        if (optionalTitle.isPresent()) {
            throw new IllegalArgumentException("해당 카테고리 타이틀이 이미 존재합니다.");
        }

        Optional<Category> optionalUrl = categoryRepository.findByUrl(reqDto.getUrl());
        if (optionalUrl.isPresent()) {
            throw new IllegalArgumentException("해당 카테고리 URL 이미 존재합니다.");
        }

        categoryRepository.save(Category.from(reqDto));
        log.info("{} 카테고리 생성", reqDto.getTitle());
    }
}
