package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.UpdateUserProfileRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface UpdateUserProfileService {
    ResponsePayload updateUserProfile(UpdateUserProfileRequest request);
}
