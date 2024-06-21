package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.DeleteProductRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface SoftDeleteProductService {
    ResponsePayload softDeleteProductById(DeleteProductRequest request);
}
