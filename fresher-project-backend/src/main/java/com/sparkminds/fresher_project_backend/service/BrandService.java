package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface BrandService {

    ResponsePayload createNewBrand(CreateBrandRequest request);
}
