package com.sparkminds.fresher_project_backend.handler.createProductHandler.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.constant.HandlerCommonConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.CreateProductHandler;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CreateProductBrandExistValidationHandlerImpl extends AbstractCreateProductHandler {
    private final BrandRepository brandRepository;

    public CreateProductBrandExistValidationHandlerImpl(ResponsePayloadUtility responsePayloadUtility, BrandRepository brandRepository) {
        super(responsePayloadUtility);
        this.brandRepository = brandRepository;
    }

    @Override
    protected boolean validate(CreateProductRequest request) {
        return brandRepository.existsById(request.getBrandId());
    }

    @Override
    protected String getSuccessMessage() {
        return "Validate brand exist" + HandlerCommonConstant.HANDLE_TASK_SUCCESS;
    }

    @Override
    protected String getErrorMessage() {
        return BrandConstant.INVALID_BRAND_NOT_EXIST;
    }

}
