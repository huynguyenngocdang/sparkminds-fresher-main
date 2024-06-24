package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.RoleConstant;
import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserProfileRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserRequest;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.entity.Role;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserProfile;
import com.sparkminds.fresher_project_backend.entity.UserRole;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.mapper.UserMapper;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.repository.RoleRepository;
import com.sparkminds.fresher_project_backend.repository.UserProfileRepository;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.repository.UserRoleRepository;
import com.sparkminds.fresher_project_backend.service.UserService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ResponsePayloadUtility responsePayloadUtility;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserProfileRepository userProfileRepository;
    private final ProductRepository productRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public ResponsePayload createUser(CreateUserRequest request) {
        Optional<User> existingUser = userRepository.findByUsernameForWrite(request.getUsername());
        if (existingUser.isPresent()) {
            return responsePayloadUtility.buildResponse(
                    UserConstant.INVALID_USER_ALREADY_EXIST,
                    HttpStatus.CONFLICT,
                    null,
                    UserConstant.INVALID_USER_ALREADY_EXIST
            );
        }
        Optional<Role> defaultRoleOpt = roleRepository.findDefaultRoleForWrite(RoleConstant.ROLE_USER);
        if (defaultRoleOpt.isEmpty()) {
            return responsePayloadUtility.buildResponse(
                    "Default role not found",
                    HttpStatus.NOT_FOUND,
                    null,
                    "Default role not found"
            );
        }

        User user = userMapper.toUserFromCreateRequest(request);

        UserProfile userProfile = userMapper.toUserProfileFromCreateRequest(request);
        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        userRepository.save(user);

        Role defaultRole = defaultRoleOpt.get();

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(defaultRole)
                .isDelete(false)
                .build();

        userRoleRepository.save(userRole);

        return responsePayloadUtility.buildResponse(
                UserConstant.CREATE_USER_SUCCESSFUL,
                HttpStatus.CREATED,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload hardDeleteUser(DeleteUserRequest request) {

        User user = userRepository.findByUserIdForWrite(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST + " userId: " + request.getUserId()));
        userRepository.deleteById(user.getId());
        return responsePayloadUtility.buildResponse(
                UserConstant.HARD_DELETE_USER_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload softDeleteUser(DeleteUserRequest request) {

        User user = userRepository.findByUserIdForWrite(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST + " userId: " + request.getUserId()));
        UserProfile userProfile = userProfileRepository.findByUserForWrite(user)
                .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_PROFILE_NOT_EXIST + " userId: " + request.getUserId()));
        List<UserRole> userRoles = userRoleRepository.findAllByUserForWrite(user);
        List<Product> products = productRepository.findAllByUserForWrite(user);

        user.setDelete(true);
        userProfile.setDelete(true);
        userRoles.forEach(userRole -> userRole.setIsDelete(true));
        if (products != null && !products.isEmpty()) {
            products.forEach(product -> product.setDelete(true));
        }
        return responsePayloadUtility.buildResponse(
                UserConstant.SOFT_DELETE_USER_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload updateUserProfile(UpdateUserProfileRequest request) {
        User user = userRepository.findByUserIdForWrite(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST));
        UserProfile userProfile = userProfileRepository.findByUserForWrite(user)
                .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_PROFILE_NOT_EXIST));

        userProfile.setFullName(request.getFullName());
        userProfile.setAge(request.getAge());
        userProfile.setGender(request.getGender());

        userProfileRepository.save(userProfile);

        return responsePayloadUtility.buildResponse(
                UserConstant.UPDATE_USER_PROFILE_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload updateUser(UpdateUserRequest request) {
        User user = userRepository.findByUserIdForWrite(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST + " userId: " + request.getUserId()));
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return responsePayloadUtility.buildResponse(
                UserConstant.UPDATE_USER_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload restoreUserService(RestoreUserRequest request) {

        User user = userRepository.findByUserIdForWrite(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST + " userId: " + request.getUserId()));
        UserProfile userProfile = userProfileRepository.findByUserForWrite(user)
                .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_PROFILE_NOT_EXIST));
        List<UserRole> userRoles = userRoleRepository.findAllByUserForWrite(user);
        List<Product> products = productRepository.findAllByUserForWrite(user);

        user.setDelete(false);
        userProfile.setDelete(false);
        userRoles.forEach(userRole -> userRole.setIsDelete(false));
        products.forEach(product -> product.setDelete(false));

        userRepository.save(user);
        userProfileRepository.save(userProfile);
        userRoleRepository.saveAll(userRoles);
        productRepository.saveAll(products);

        return responsePayloadUtility.buildResponse(
                UserConstant.SOFT_RESTORE_USER_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }
}
