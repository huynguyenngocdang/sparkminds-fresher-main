package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.LoginRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import org.springframework.security.core.Authentication;

public interface AuthService {
    ResponsePayload login(LoginRequest request);
    boolean isUserNotDelete(String username);
    boolean isProductOwnByUser(Long productId);
    boolean isAdminOrModerator();
}
