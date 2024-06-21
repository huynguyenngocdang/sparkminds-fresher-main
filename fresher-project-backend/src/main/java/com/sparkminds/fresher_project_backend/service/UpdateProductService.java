package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.UpdateProductBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductDetailsRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface UpdateProductService {
    ResponsePayload updateProductDetails(UpdateProductDetailsRequest request);
    ResponsePayload updateProductBrand(UpdateProductBrandRequest request);
    ResponsePayload updateProductCategory(UpdateProductCategoryRequest request);
}
