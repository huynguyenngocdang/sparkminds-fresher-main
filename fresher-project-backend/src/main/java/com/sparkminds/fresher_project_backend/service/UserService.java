package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface UserService {
    ResponsePayload createUser(CreateUserRequest request);
}
