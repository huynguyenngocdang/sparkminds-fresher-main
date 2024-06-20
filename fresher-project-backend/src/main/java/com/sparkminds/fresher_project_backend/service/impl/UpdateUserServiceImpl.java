package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserRequest;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.handler.updateUserHandler.impl.UpdateUserExistValidationHandlerImpl;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.service.UpdateUserService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserServiceImpl implements UpdateUserService {
    private final UserRepository userRepository;
    private final ResponsePayloadUtility responsePayloadUtility;

    private final UpdateUserExistValidationHandlerImpl updateUserExistValidationHandler;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload updateUser(UpdateUserRequest request) {
        try {
            updateUserExistValidationHandler.setNextHandler(null);
            ResponsePayload responsePayload = updateUserExistValidationHandler.handle(request);
            if(!responsePayload.getStatus().is2xxSuccessful()) {
                return responsePayload;
            }
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST));
            user.setPassword(request.getPassword());
            userRepository.save(user);
            return responsePayloadUtility.buildResponse(
                    UserConstant.UPDATE_USER_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    UserConstant.UPDATE_USER_FAIL
            );
        }
    }
}
