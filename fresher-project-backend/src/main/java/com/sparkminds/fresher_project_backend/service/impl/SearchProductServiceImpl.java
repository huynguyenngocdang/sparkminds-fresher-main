package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.ProductSearchConstant;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByNameRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByPriceRangeRequest;
import com.sparkminds.fresher_project_backend.dto.response.SearchProductResponse;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.service.SearchProductService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProductServiceImpl implements SearchProductService {
    private final ProductRepository productRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    public ResponsePayload searchProductsByName(SearchProductByNameRequest request) {
        List<Product> products = productRepository.findProductByProductNameQuery(request.getQuery());
        List<SearchProductResponse> productResponses = products.stream().map(product ->
                SearchProductResponse.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .brandId(product.getBrand() != null ? product.getBrand().getId() : null)
                        .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                        .userId(product.getUser() != null ? product.getUser().getId() : null)
                        .isDelete(product.isDelete())
                        .build()
        ).toList();

        return responsePayloadUtility.buildResponse(
                ProductSearchConstant.PRODUCT_SEARCH_SUCCESS,
                HttpStatus.OK,
                productResponses,
                null
        );
    }

    @Override
    public ResponsePayload searchProductByPrice(SearchProductByPriceRangeRequest request) {
        List<Product> products = productRepository.findProductByDynamicPriceRange(request.getPriceMin(), request.getPriceMax());
        List<SearchProductResponse> productResponses = products.stream().map(product ->
                SearchProductResponse.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .brandId(product.getBrand() != null ? product.getBrand().getId() : null)
                        .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                        .userId(product.getUser() != null ? product.getUser().getId() : null)
                        .build()
        ).toList();
        return responsePayloadUtility.buildResponse(
                ProductSearchConstant.PRODUCT_SEARCH_SUCCESS,
                HttpStatus.OK,
                productResponses,
                null
        );
    }
}
