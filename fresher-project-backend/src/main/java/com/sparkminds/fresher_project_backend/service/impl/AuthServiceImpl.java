package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.dto.request.LoginRequest;
import com.sparkminds.fresher_project_backend.dto.response.LoginResponse;
import com.sparkminds.fresher_project_backend.dto.response.RoleResponse;
import com.sparkminds.fresher_project_backend.entity.Role;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.mapper.RoleMapper;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.RoleRepository;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.service.JwtService;
import com.sparkminds.fresher_project_backend.service.AuthService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ResponsePayloadUtility responsePayloadUtility;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    @Override
    @Transactional
    public ResponsePayload login(LoginRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
        } catch (AuthenticationException e) {
            return responsePayloadUtility.buildResponse(
                    "Login fail",
                    HttpStatus.NOT_FOUND,
                    null,
                    e.getMessage()
            );
        }
        if (authentication.isAuthenticated() && isUserNotDelete(request.getUsername())) {
            List<Role> roles = roleRepository.findAllRoleForRead(request.getUsername());
            List<RoleResponse> roleResponses = mapRoles(roles);
            String accessToken = jwtService.generateToken(request.getUsername());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(accessToken);
            loginResponse.setUsername(request.getUsername());
            loginResponse.setRoles(roleResponses);
            return responsePayloadUtility.buildResponse(
                    "Login successfully",
                    HttpStatus.OK,
                    loginResponse,
                    null
            );
        }
        return responsePayloadUtility.buildResponse(
                "Login fail",
                HttpStatus.NOT_FOUND,
                null,
                "Login fail"
        );
    }

    private List<RoleResponse> mapRoles(List<Role> roles) {
        return roles.stream()
                .map(this::mapRoleToRoleResponse)
                .collect(Collectors.toList());
    }

    private RoleResponse mapRoleToRoleResponse(Role role) {
        return roleMapper.toRoleResponseFromRole(role);
    }

    @Override
    public boolean isUserNotDelete(String username) {
        User user = userRepository.findFirstByUsername(username);
        return !user.isDelete();
    }
}
