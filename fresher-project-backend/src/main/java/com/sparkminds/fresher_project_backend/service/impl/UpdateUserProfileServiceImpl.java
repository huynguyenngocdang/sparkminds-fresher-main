package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserProfileRequest;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserProfile;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.UserProfileRepository;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.service.UpdateUserProfileService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileServiceImpl implements UpdateUserProfileService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload updateUserProfile(UpdateUserProfileRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST));
            UserProfile userProfile = userProfileRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_PROFILE_NOT_EXIST));

            userProfile.setFullName(request.getFullName());
            userProfile.setAge(request.getAge());
            userProfile.setGender(request.getGender());

            userProfileRepository.save(userProfile);

            return responsePayloadUtility.buildResponse(
                    UserConstant.UPDATE_USER_PROFILE_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null,
                    UserConstant.UPDATE_USER_PROFILE_FAIL
            );
        }

    }
}
