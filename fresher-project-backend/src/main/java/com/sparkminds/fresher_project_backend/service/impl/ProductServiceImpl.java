package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.constant.CategoryConstant;
import com.sparkminds.fresher_project_backend.constant.ProductConstant;
import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.impl.BrandExistValidationHandler;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.impl.CategoryExistValidationHandler;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.impl.ProductExistValidationHandler;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.impl.UserExistValidationHandler;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.repository.CategoryRepository;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.service.ProductService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ResponsePayloadUtility responsePayloadUtility;

    private final BrandExistValidationHandler brandExistValidationHandler;
    private final CategoryExistValidationHandler categoryExistValidationHandler;
    private final ProductExistValidationHandler productExistValidationHandler;
    private final UserExistValidationHandler userExistValidationHandler;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload createNewProduct(CreateProductRequest request) {
        try {
            userExistValidationHandler.setNextHandler(brandExistValidationHandler);
            brandExistValidationHandler.setNextHandler(categoryExistValidationHandler);
            categoryExistValidationHandler.setNextHandler(productExistValidationHandler);
            productExistValidationHandler.setNextHandler(null);

            ResponsePayload responsePayload = userExistValidationHandler.handle(request);
            if(!responsePayload.getStatus().is2xxSuccessful()) {
                return responsePayload;
            }

            Product product = Product.builder()
                    .productName(request.getProductName())
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .brand(brandRepository.findByBrandName(
                            request.getBrandName()).orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST)))
                    .category(categoryRepository.findByCategoryName(
                            request.getCategoryName()).orElseThrow(()-> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST)))
                    .user(userRepository.findByUsername(
                            request.getUsername()).orElseThrow(()-> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST)))
                    .build();
            productRepository.save(product);
            return responsePayloadUtility.buildResponse(
                    ProductConstant.CREATE_PRODUCT_SUCCESSFUL,
                    HttpStatus.CREATED,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    ProductConstant.CREATE_PRODUCT_FAIL,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    ProductConstant.CREATE_PRODUCT_FAIL
            );
        }
    }
}
