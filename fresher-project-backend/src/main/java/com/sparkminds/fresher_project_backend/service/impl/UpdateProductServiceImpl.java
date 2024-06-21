package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.constant.CategoryConstant;
import com.sparkminds.fresher_project_backend.constant.ProductConstant;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductDetailsRequest;
import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.entity.Category;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.repository.CategoryRepository;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.service.UpdateProductService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateProductServiceImpl implements UpdateProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload updateProductDetails(UpdateProductDetailsRequest request) {
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductConstant.INVALID_PRODUCT_NOT_EXIST + " id: " + request.getProductId()));

            if(productRepository.existsByProductName(request.getProductName())) {
                return responsePayloadUtility.buildResponse(
                        ProductConstant.UPDATE_PRODUCT_DETAILS_FAIL,
                        HttpStatus.BAD_REQUEST,
                        null,
                        ProductConstant.INVALID_PRODUCT_ALREADY_EXIST + request.getProductName()
                );
            }

            product.setProductName(request.getProductName());
            product.setPrice(request.getPrice());
            product.setQuantity(request.getQuantity());

            productRepository.save(product);

            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_DETAILS_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );

        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_DETAILS_FAIL,
                    HttpStatus.BAD_REQUEST,
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload updateProductBrand(UpdateProductBrandRequest request) {
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductConstant.INVALID_PRODUCT_NOT_EXIST + " productId: " + request.getProductId()));

            Brand brand = brandRepository.findById(request.getNewBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandId: " + request.getNewBrandId()));

            product.setBrand(brand);
            productRepository.save(product);
            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_BRAND_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_BRAND_FAIL,
                    HttpStatus.BAD_REQUEST,
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload updateProductCategory(UpdateProductCategoryRequest request) {
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductConstant.INVALID_PRODUCT_NOT_EXIST + " productId: " + request.getProductId()));

            Category category = categoryRepository.findById(request.getNewCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryId: " + request.getNewCategoryId()));

            product.setCategory(category);
            productRepository.save(product);
            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_CATEGORY_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_CATEGORY_FAIL,
                    HttpStatus.BAD_REQUEST,
                    null,
                    e.getMessage()
            );
        }
    }
}
