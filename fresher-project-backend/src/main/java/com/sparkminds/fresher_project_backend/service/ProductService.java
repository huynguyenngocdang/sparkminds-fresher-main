package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductsRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductDetailsRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ResponsePayload createNewProduct(CreateProductRequest request);
    ResponsePayload searchProducts(SearchProductsRequest request, Pageable pageable);
    ResponsePayload hardDeleteProductById(DeleteProductRequest request);
    ResponsePayload softDeleteProductById(DeleteProductRequest request);
    ResponsePayload softRestoreProductById(RestoreProductRequest request);
    ResponsePayload updateProductDetails(UpdateProductDetailsRequest request);
    ResponsePayload updateProductBrand(UpdateProductBrandRequest request);
    ResponsePayload updateProductCategory(UpdateProductCategoryRequest request);
}
