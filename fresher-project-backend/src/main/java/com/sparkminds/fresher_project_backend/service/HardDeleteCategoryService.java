package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.DeleteCategoryRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface HardDeleteCategoryService {
    ResponsePayload hardDeleteCategoryById(DeleteCategoryRequest request);
}
