package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface CreateBrandService {

    ResponsePayload createNewBrand(CreateBrandRequest request);
}
