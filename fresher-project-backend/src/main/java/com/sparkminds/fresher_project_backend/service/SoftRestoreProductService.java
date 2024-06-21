package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.RestoreCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreProductRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface SoftRestoreProductService {
    ResponsePayload softRestoreProductById(RestoreProductRequest request);
}
