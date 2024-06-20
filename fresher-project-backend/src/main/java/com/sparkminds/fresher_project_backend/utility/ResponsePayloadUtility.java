package com.sparkminds.fresher_project_backend.utility;

import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public interface ResponsePayloadUtility {
     ResponsePayload buildResponse(String message, HttpStatus status, Object data, String error);

}
