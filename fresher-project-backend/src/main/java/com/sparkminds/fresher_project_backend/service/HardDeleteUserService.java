package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.DeleteUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface HardDeleteUserService {
    ResponsePayload hardDeleteUser(DeleteUserRequest request);
}
