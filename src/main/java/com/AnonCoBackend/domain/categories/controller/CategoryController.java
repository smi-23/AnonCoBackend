package com.AnonCoBackend.domain.categories.controller;

import com.AnonCoBackend.domain.categories.dto.CategoryReqDto;
import com.AnonCoBackend.domain.categories.dto.CategoryResDto;
import com.AnonCoBackend.domain.categories.service.CategoryService;
import com.AnonCoBackend.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{categoryUrl}")
    public ResponseEntity<Message> getCategoryByUrl(@PathVariable("categoryUrl") String categoryUrl) {

        CategoryResDto resDto = categoryService.getCategoryByUrl(categoryUrl);
        return new ResponseEntity<>(new Message(categoryUrl + "의 카테고리 타이틀은 " + resDto.getTitle() + "입니다.", resDto), HttpStatus.OK);
    }
}
