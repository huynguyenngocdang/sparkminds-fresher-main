package com.sparkminds.fresher_project_backend.handler.createProductHandler.impl;

import com.sparkminds.fresher_project_backend.constant.CategoryConstant;
import com.sparkminds.fresher_project_backend.constant.HandlerCommonConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.CreateProductHandler;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.CategoryRepository;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service

public class CreateProductCategoryExistValidationHandlerImpl extends AbstractCreateProductHandler {
    private final CategoryRepository categoryRepository;

    public CreateProductCategoryExistValidationHandlerImpl(ResponsePayloadUtility responsePayloadUtility, CategoryRepository categoryRepository) {
        super(responsePayloadUtility);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected boolean validate(CreateProductRequest request) {
        return categoryRepository.existsById(request.getCategoryId());
    }

    @Override
    protected String getSuccessMessage() {
        return "Validate category exist" + HandlerCommonConstant.HANDLE_TASK_SUCCESS;
    }

    @Override
    protected String getErrorMessage() {
        return CategoryConstant.INVALID_CATEGORY_NOT_EXIST;
    }

}
