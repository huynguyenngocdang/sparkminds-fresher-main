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
@RequiredArgsConstructor
public class UserExistValidationHandler implements CreateProductHandler {
    private final UserRepository userRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    private CreateProductHandler nextHandler;
    @Override
    public void setNextHandler(CreateProductHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public ResponsePayload handle(CreateProductRequest request) {
        if(!userRepository.existsByUsername(request.getUsername())) {
            return responsePayloadUtility.buildResponse(
                    UserConstant.INVALID_USER_NOT_EXIST,
                    HttpStatus.BAD_REQUEST,
                    null,
                    UserConstant.INVALID_USER_NOT_EXIST
            );
        }
        if(nextHandler!= null) {
            return nextHandler.handle(request);
        }

        String taskHandleMessage = "Validate user exist" + HandlerCommonConstant.HANDLE_TASK_SUCCESS;
        return responsePayloadUtility.buildResponse(
                taskHandleMessage,
                HttpStatus.ACCEPTED,
                null,
                null
        );
    }
}
