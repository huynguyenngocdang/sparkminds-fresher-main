package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateBrandNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface BrandService {
    ResponsePayload createNewBrand(CreateBrandRequest request);
    ResponsePayload hardDeleteBrandById(DeleteBrandRequest request);
    ResponsePayload softDeleteBrandById(DeleteBrandRequest request);
    ResponsePayload softRestoreBrandById(RestoreBrandRequest request);
    ResponsePayload updateBrandName(UpdateBrandNameRequest request);

}
