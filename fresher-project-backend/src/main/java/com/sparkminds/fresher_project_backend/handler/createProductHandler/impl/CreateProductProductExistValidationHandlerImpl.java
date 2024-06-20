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
@RequiredArgsConstructor
public class CreateProductProductExistValidationHandlerImpl implements CreateProductHandler {
    private final ProductRepository productRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    private CreateProductHandler nextHandler;
    @Override
    public void setNextHandler(CreateProductHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public ResponsePayload handle(CreateProductRequest request) {
        if(productRepository.existsByProductName(request.getProductName())) {
            return responsePayloadUtility.buildResponse(
                    ProductConstant.INVALID_PRODUCT_ALREADY_EXIST,
                    HttpStatus.BAD_REQUEST,
                    null,
                    ProductConstant.INVALID_PRODUCT_ALREADY_EXIST
            );
        }
        if(nextHandler!= null) {
            return nextHandler.handle(request);
        }

        String taskHandleMessage = "Validate product not exist" + HandlerCommonConstant.HANDLE_TASK_SUCCESS;
        return responsePayloadUtility.buildResponse(
                taskHandleMessage,
                HttpStatus.ACCEPTED,
                null,
                null
        );
    }
}
