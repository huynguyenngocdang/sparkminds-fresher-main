package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.DeleteUserRequest;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserProfile;
import com.sparkminds.fresher_project_backend.entity.UserRole;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.repository.UserProfileRepository;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.repository.UserRoleRepository;
import com.sparkminds.fresher_project_backend.service.SoftDeleteUserService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftDeleteUserServiceImpl implements SoftDeleteUserService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserRoleRepository userRoleRepository;
    private final ProductRepository productRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload softDeleteUser(DeleteUserRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST));
            UserProfile userProfile = userProfileRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_PROFILE_NOT_EXIST));
            List<UserRole> userRoles = userRoleRepository.findAllByUser(user);
            List<Product> products = productRepository.findAllByUser(user);

            user.setDelete(true);
            userProfile.setDelete(true);
            userRoles.forEach(userRole -> userRole.setIsDelete(true));
            products.forEach(product -> product.setDelete(true));

            userRepository.save(user);
            userProfileRepository.save(userProfile);
            userRoleRepository.saveAll(userRoles);
            productRepository.saveAll(products);

            return responsePayloadUtility.buildResponse(
                    UserConstant.SOFT_DELETE_USER_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null,
                    UserConstant.SOFT_DELETE_USER_FAIL
            );
        }
    }
}
