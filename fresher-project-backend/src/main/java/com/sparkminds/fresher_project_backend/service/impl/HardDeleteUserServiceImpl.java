package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.DeleteUserRequest;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.repository.UserRoleRepository;
import com.sparkminds.fresher_project_backend.service.HardDeleteUserService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HardDeleteUserServiceImpl implements HardDeleteUserService {
    private final UserRepository userRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    private final UserRoleRepository userRoleRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload hardDeleteUser(DeleteUserRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST));

            //            user.getUserRoles().forEach(userRole -> userRoleRepository.deleteById(userRole.getId()));

            userRepository.deleteById(user.getId());
            return responsePayloadUtility.buildResponse(
                    UserConstant.HARD_DELETE_USER_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null,
                    null
            );
        }
    }
}
