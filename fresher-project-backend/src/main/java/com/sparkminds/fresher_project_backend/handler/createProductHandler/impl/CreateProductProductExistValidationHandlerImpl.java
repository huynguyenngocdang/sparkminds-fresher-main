package com.sparkminds.fresher_project_backend.handler.createProductHandler.impl;

import com.sparkminds.fresher_project_backend.constant.HandlerCommonConstant;
import com.sparkminds.fresher_project_backend.constant.ProductConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.CreateProductHandler;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service

public class CreateProductProductExistValidationHandlerImpl extends AbstractCreateProductHandler {
    private final ProductRepository productRepository;

    public CreateProductProductExistValidationHandlerImpl(ResponsePayloadUtility responsePayloadUtility, ProductRepository productRepository) {
        super(responsePayloadUtility);
        this.productRepository = productRepository;
    }

    @Override
    protected boolean validate(CreateProductRequest request) {
        return !productRepository.existsByProductName(request.getProductName());
    }

    @Override
    protected String getSuccessMessage() {
        return "Validate product not exist" + HandlerCommonConstant.HANDLE_TASK_SUCCESS;
    }

    @Override
    protected String getErrorMessage() {
        return ProductConstant.INVALID_PRODUCT_ALREADY_EXIST;
    }

}
