package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateCategoryNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CreateCategoryService;
import com.sparkminds.fresher_project_backend.service.HardDeleteCategoryService;
import com.sparkminds.fresher_project_backend.service.SoftDeleteCategoryService;
import com.sparkminds.fresher_project_backend.service.SoftRestoreCategoryService;
import com.sparkminds.fresher_project_backend.service.UpdateCategoryService;
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
    private final CreateCategoryService createCategoryService;
    private final UpdateCategoryService updateCategoryService;
    private final HardDeleteCategoryService hardDeleteCategoryService;
    private final SoftDeleteCategoryService softDeleteCategoryService;
    private final SoftRestoreCategoryService softRestoreCategoryService;
    @PostMapping("/create")
    public ResponseEntity<ResponsePayload> createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        ResponsePayload responsePayload = createCategoryService.createCategory(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PutMapping("/update")
    public ResponseEntity<ResponsePayload> updateCategoryName(@RequestBody @Valid UpdateCategoryNameRequest request) {
        ResponsePayload responsePayload = updateCategoryService.updateCategoryName(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/hard-delete-category")
    public ResponseEntity<ResponsePayload> hardDeleteCategory(@RequestBody @Valid DeleteCategoryRequest request) {
        ResponsePayload responsePayload = hardDeleteCategoryService.hardDeleteCategoryById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/soft-delete-category")
    public ResponseEntity<ResponsePayload> softDeleteCategory(@RequestBody @Valid DeleteCategoryRequest request) {
        ResponsePayload responsePayload = softDeleteCategoryService.softDeleteCategoryById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PatchMapping("/soft-restore-category")
    public ResponseEntity<ResponsePayload> softRestoreCategory(@RequestBody @Valid RestoreCategoryRequest request) {
        ResponsePayload responsePayload = softRestoreCategoryService.softRestoreCategoryById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
