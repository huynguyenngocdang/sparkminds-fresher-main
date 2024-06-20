package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.UpdateUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface UpdateUserService {
    ResponsePayload updateUser(UpdateUserRequest request);
}
