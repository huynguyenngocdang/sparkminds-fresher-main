package com.sparkminds.fresher_project_backend.handler.createProductHandler;

import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface CreateProductHandler {
    void setNextHandler(CreateProductHandler handler);
    ResponsePayload handle(CreateProductRequest request);
}
