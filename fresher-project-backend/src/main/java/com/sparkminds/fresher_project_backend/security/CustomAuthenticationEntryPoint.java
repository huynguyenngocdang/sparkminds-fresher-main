package com.sparkminds.fresher_project_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparkminds.fresher_project_backend.constant.CommonConstant;
import com.sparkminds.fresher_project_backend.constant.CommonErrorConstant;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponsePayload responsePayload = responsePayloadUtility.buildResponse(
                CommonErrorConstant.RESPONSE_UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED,
                null,
                CommonErrorConstant.RESPONSE_ACCESS_DENIED
        );
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(responsePayload));
    }
}
