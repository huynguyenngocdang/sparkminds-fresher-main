package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateCategoryNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface CategoryService {
    ResponsePayload createCategory(CreateCategoryRequest createCategoryRequest);
    ResponsePayload hardDeleteCategoryById(DeleteCategoryRequest request);
    ResponsePayload softDeleteCategoryById(DeleteCategoryRequest request);
    ResponsePayload softRestoreCategoryById(RestoreCategoryRequest request);
    ResponsePayload updateCategoryName(UpdateCategoryNameRequest request);
}
