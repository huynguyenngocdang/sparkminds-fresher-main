package com.sparkminds.fresher_project_backend.handler.createProductHandler.impl;

import com.sparkminds.fresher_project_backend.constant.HandlerCommonConstant;
import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.CreateProductHandler;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service

public class CreateProductUserExistValidationHandlerImpl extends AbstractCreateProductHandler {
    private final UserRepository userRepository;

    public CreateProductUserExistValidationHandlerImpl(ResponsePayloadUtility responsePayloadUtility, UserRepository userRepository) {
        super(responsePayloadUtility);
        this.userRepository = userRepository;
    }

    @Override
    protected boolean validate(CreateProductRequest request) {
        return userRepository.existsById(request.getUserId());
    }

    @Override
    protected String getSuccessMessage() {
        return "Validate user exist" + HandlerCommonConstant.HANDLE_TASK_SUCCESS;
    }

    @Override
    protected String getErrorMessage() {
        return UserConstant.INVALID_USER_NOT_EXIST;
    }

}
