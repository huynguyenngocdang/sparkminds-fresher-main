package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface CreateProductService {
    ResponsePayload createNewProduct(CreateProductRequest request);
}
