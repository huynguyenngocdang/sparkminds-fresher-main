package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.RoleConstant;
import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.entity.Role;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserProfile;
import com.sparkminds.fresher_project_backend.entity.UserRole;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.RoleRepository;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.repository.UserRoleRepository;
import com.sparkminds.fresher_project_backend.service.UserService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final ResponsePayloadUtility responsePayloadUtility;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload createUser(CreateUserRequest request) {
        try {
            if (userRepository.existsByUsername(request.getUsername())) {
                return responsePayloadUtility.buildResponse(
                        UserConstant.INVALID_USER_ALREADY_EXIST,
                        HttpStatus.CONFLICT,
                        null,
                        UserConstant.INVALID_USER_ALREADY_EXIST
                );
            }

            Role defaultRole = roleRepository.findDefaultRole(RoleConstant.ROLE_USER);
            if (defaultRole == null) {
                return responsePayloadUtility.buildResponse(
                        "Default role not found",
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        null,
                        "Default role not found"
                );
            }

            // Create User entity
            User user = User.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .isDelete(false)
                    .build();

            // Create UserProfile entity
            UserProfile userProfile = UserProfile.builder()
                    .fullName(request.getFullName())
                    .age(request.getAge())
                    .gender(request.getGender())
                    .isDelete(false)
                    .user(user)
                    .build();

            user.setUserProfile(userProfile); // Set the UserProfile reference in User

            // Save User (this will also save UserProfile due to cascading)
            userRepository.save(user);

            // Create UserRole entity
            UserRole userRole = UserRole.builder()
                    .user(user)
                    .role(defaultRole)
                    .build();

            // Save UserRole
            userRoleRepository.save(userRole);

            return responsePayloadUtility.buildResponse(
                    UserConstant.CREATE_USER_SUCCESSFUL,
                    HttpStatus.CREATED,
                    user,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    UserConstant.CREATE_USER_FAIL,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    UserConstant.CREATE_USER_FAIL
            );
        }
    }
}
