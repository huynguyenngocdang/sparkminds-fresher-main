package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateCategoryRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface CreateCategoryService {
    ResponsePayload createCategory(CreateCategoryRequest createCategoryRequest);
}
