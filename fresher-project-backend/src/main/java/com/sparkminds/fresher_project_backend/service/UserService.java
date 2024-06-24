package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserProfileRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface UserService {
    ResponsePayload createUser(CreateUserRequest request);
    ResponsePayload updateUser(UpdateUserRequest request);
    ResponsePayload updateUserProfile(UpdateUserProfileRequest request);
    ResponsePayload hardDeleteUser(DeleteUserRequest request);
    ResponsePayload softDeleteUser(DeleteUserRequest request);
    ResponsePayload restoreUserService(RestoreUserRequest request);

}
