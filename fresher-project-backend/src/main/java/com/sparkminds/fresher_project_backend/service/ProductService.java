package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByNameRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByPriceRangeRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductsRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductDetailsRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface ProductService {
    ResponsePayload createNewProduct(CreateProductRequest request);
    ResponsePayload searchProducts(SearchProductsRequest request);
    ResponsePayload searchProductsByName(SearchProductByNameRequest request);
    ResponsePayload searchProductByPrice(SearchProductByPriceRangeRequest request);
    ResponsePayload searchProductByCategory(SearchProductByCategoryRequest request);
    ResponsePayload hardDeleteProductById(DeleteProductRequest request);
    ResponsePayload softDeleteProductById(DeleteProductRequest request);
    ResponsePayload softRestoreProductById(RestoreProductRequest request);
    ResponsePayload updateProductDetails(UpdateProductDetailsRequest request);
    ResponsePayload updateProductBrand(UpdateProductBrandRequest request);
    ResponsePayload updateProductCategory(UpdateProductCategoryRequest request);
}
