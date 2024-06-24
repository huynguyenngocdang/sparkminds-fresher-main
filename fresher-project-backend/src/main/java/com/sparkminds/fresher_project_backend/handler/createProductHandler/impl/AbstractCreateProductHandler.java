package com.sparkminds.fresher_project_backend.handler.createProductHandler.impl;

import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.CreateProductHandler;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public abstract class AbstractCreateProductHandler implements CreateProductHandler {
    protected final ResponsePayloadUtility responsePayloadUtility;
    protected CreateProductHandler nextHandler;
    @Override
    public void setNextHandler(CreateProductHandler handler) {
        this.nextHandler = handler;
    }

    protected abstract boolean validate(CreateProductRequest request);

    protected abstract String getSuccessMessage();

    protected abstract String getErrorMessage();

    @Override
    public ResponsePayload handle(CreateProductRequest request) {
        if (!validate(request)) {
            return responsePayloadUtility.buildResponse(
                    getErrorMessage(),
                    HttpStatus.BAD_REQUEST,
                    null,
                    getErrorMessage()
            );
        }
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return responsePayloadUtility.buildResponse(
                getSuccessMessage(),
                HttpStatus.ACCEPTED,
                null,
                null
        );
    }
}
