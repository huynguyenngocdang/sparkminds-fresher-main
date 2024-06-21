package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.RestoreBrandRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface SoftRestoreBrandService {
    ResponsePayload softRestoreBrandById(RestoreBrandRequest request);
}
