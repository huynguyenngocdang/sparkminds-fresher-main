package com.sparkminds.fresher_project_backend.utility.impl;

import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponsePayloadUtiltyImpl implements ResponsePayloadUtility {
    @Override
    public ResponsePayload buildResponse(String message, HttpStatus status, Object data, String error){
        return ResponsePayload.builder()
                .message(message)
                .status(status)
                .data(data)
                .error(error)
                .build();
    }
}
