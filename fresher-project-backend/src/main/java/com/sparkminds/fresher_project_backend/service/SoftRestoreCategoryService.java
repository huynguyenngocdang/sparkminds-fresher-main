package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.RestoreBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreCategoryRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface SoftRestoreCategoryService {
    ResponsePayload softRestoreCategoryById(RestoreCategoryRequest request);
}
