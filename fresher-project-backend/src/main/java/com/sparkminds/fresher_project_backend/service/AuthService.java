package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.LoginRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface AuthService {
    ResponsePayload login(LoginRequest request);
    boolean isUserNotDelete(String username);
}
