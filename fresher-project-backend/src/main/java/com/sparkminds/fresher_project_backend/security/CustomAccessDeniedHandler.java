package com.sparkminds.fresher_project_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparkminds.fresher_project_backend.constant.CommonErrorConstant;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponsePayload responsePayload = responsePayloadUtility.buildResponse(
                CommonErrorConstant.RESPONSE_FORBIDDEN,
                HttpStatus.UNAUTHORIZED,
                null,
                CommonErrorConstant.RESPONSE_ACCESS_DENIED
        );
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(responsePayload));
    }
}
