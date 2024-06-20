package com.sparkminds.fresher_project_backend.handler.updateUserHandler;

import com.sparkminds.fresher_project_backend.dto.request.UpdateUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface UpdateUserHandler {
    void setNextHandler(UpdateUserHandler handler);
    ResponsePayload handle(UpdateUserRequest request);
}
