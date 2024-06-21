package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.DeleteBrandRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface HardDeleteBrandService {
    ResponsePayload hardDeleteBrandById(DeleteBrandRequest request);
}
