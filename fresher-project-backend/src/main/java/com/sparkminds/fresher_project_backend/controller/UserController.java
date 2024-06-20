package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserProfileRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CreateUserService;
import com.sparkminds.fresher_project_backend.service.HardDeleteUserService;
import com.sparkminds.fresher_project_backend.service.SoftDeleteUserService;
import com.sparkminds.fresher_project_backend.service.SoftRestoreUserService;
import com.sparkminds.fresher_project_backend.service.UpdateUserProfileService;
import com.sparkminds.fresher_project_backend.service.UpdateUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;
    private final UpdateUserProfileService updateUserProfileService;
    private final HardDeleteUserService hardDeleteUserService;
    private final SoftDeleteUserService softDeleteUserService;
    private final SoftRestoreUserService softRestoreUserService;

    @PostMapping("/create")
    public ResponseEntity<ResponsePayload> createNewUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        ResponsePayload responsePayload = createUserService.createUser(createUserRequest);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PutMapping("/update-user")
    public ResponseEntity<ResponsePayload> updateUser(@RequestBody @Valid UpdateUserRequest request) {
        ResponsePayload responsePayload = updateUserService.updateUser(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PutMapping("/update-profile")
    public ResponseEntity<ResponsePayload> updateUserProfile(@RequestBody @Valid UpdateUserProfileRequest request) {
        ResponsePayload responsePayload = updateUserProfileService.updateUserProfile(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/hard-delete-user")
    public ResponseEntity<ResponsePayload> hardDeleteUser(@RequestBody @Valid DeleteUserRequest request) {
        ResponsePayload responsePayload = hardDeleteUserService.hardDeleteUser(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @DeleteMapping("/soft-delete-user")
    public ResponseEntity<ResponsePayload> softDeleteUser(@RequestBody @Valid DeleteUserRequest request) {
        ResponsePayload responsePayload = softDeleteUserService.softDeleteUser(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PatchMapping("/restore-user")
    public ResponseEntity<ResponsePayload> softDeleteUser(@RequestBody @Valid RestoreUserRequest request) {
        ResponsePayload responsePayload = softRestoreUserService.restoreUserService(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
