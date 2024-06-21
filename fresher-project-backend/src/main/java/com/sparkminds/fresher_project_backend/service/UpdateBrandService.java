package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.UpdateBrandNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface UpdateBrandService {
    ResponsePayload updateBrandName(UpdateBrandNameRequest request);
}
