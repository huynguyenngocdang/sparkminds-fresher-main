package com.sparkminds.fresher_project_backend.utility;

import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponsePayloadUtility {
    public ResponsePayload buildResponse(String message, HttpStatus status, Object data, String error){
        return ResponsePayload.builder()
                .message(message)
                .status(status)
                .data(data)
                .error(error)
                .build();
    }
}
