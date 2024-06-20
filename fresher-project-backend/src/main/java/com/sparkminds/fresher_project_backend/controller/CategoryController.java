package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateCategoryRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<ResponsePayload> createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        ResponsePayload responsePayload = categoryService.createCategory(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
