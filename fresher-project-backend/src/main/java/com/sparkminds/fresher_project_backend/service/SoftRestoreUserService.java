package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.RestoreUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface SoftRestoreUserService {
    ResponsePayload restoreUserService(RestoreUserRequest request);
}
