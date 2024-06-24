package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateCategoryNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<ResponsePayload> createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        ResponsePayload responsePayload = categoryService.createCategory(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PutMapping
    public ResponseEntity<ResponsePayload> updateCategoryName(@RequestBody @Valid UpdateCategoryNameRequest request) {
        ResponsePayload responsePayload = categoryService.updateCategoryName(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/hard")
    public ResponseEntity<ResponsePayload> hardDeleteCategory(@RequestBody @Valid DeleteCategoryRequest request) {
        ResponsePayload responsePayload = categoryService.hardDeleteCategoryById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping
    public ResponseEntity<ResponsePayload> softDeleteCategory(@RequestBody @Valid DeleteCategoryRequest request) {
        ResponsePayload responsePayload = categoryService.softDeleteCategoryById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PatchMapping
    public ResponseEntity<ResponsePayload> softRestoreCategory(@RequestBody @Valid RestoreCategoryRequest request) {
        ResponsePayload responsePayload = categoryService.softRestoreCategoryById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
