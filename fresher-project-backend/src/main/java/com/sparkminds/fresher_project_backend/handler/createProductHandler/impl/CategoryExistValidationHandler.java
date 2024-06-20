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
@RequiredArgsConstructor
public class CategoryExistValidationHandler implements CreateProductHandler {
    private final CategoryRepository categoryRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    private CreateProductHandler nextHandler;
    @Override
    public void setNextHandler(CreateProductHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public ResponsePayload handle(CreateProductRequest request) {
        if(!categoryRepository.existsByCategoryName(request.getCategoryName())) {
            return responsePayloadUtility.buildResponse(
                    CategoryConstant.INVALID_CATEGORY_NOT_EXIST,
                    HttpStatus.BAD_REQUEST,
                    null,
                    CategoryConstant.INVALID_CATEGORY_NOT_EXIST
            );
        }
        if(nextHandler!= null) {
            return nextHandler.handle(request);
        }

        String taskHandleMessage = "Validate category exist" + HandlerCommonConstant.HANDLE_TASK_SUCCESS;
        return responsePayloadUtility.buildResponse(
                taskHandleMessage,
                HttpStatus.ACCEPTED,
                null,
                null
        );
    }
}
