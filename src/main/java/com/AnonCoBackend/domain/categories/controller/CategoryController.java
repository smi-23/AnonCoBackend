package com.AnonCoBackend.domain.categories.controller;

import com.AnonCoBackend.domain.categories.dto.CategoryReqDto;
import com.AnonCoBackend.domain.categories.service.CategoryService;
import com.AnonCoBackend.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Message> createCategory(@RequestBody CategoryReqDto reqDto) {
        categoryService.createCategory(reqDto);
        return new ResponseEntity<>(new Message(reqDto.getTitle() + " 카테고리가 생성되었습니다.", null), HttpStatus.CREATED);
    }
}
