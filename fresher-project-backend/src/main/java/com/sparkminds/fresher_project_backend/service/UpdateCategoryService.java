package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.UpdateCategoryNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface UpdateCategoryService {
    ResponsePayload updateCategoryName(UpdateCategoryNameRequest request);
}
