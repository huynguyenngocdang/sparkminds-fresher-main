package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserProfileRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.UserService;
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
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponsePayload> createNewUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        ResponsePayload responsePayload = userService.createUser(createUserRequest);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PutMapping
    public ResponseEntity<ResponsePayload> updateUser(@RequestBody @Valid UpdateUserRequest request) {
        ResponsePayload responsePayload = userService.updateUser(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PutMapping("/profile")
    public ResponseEntity<ResponsePayload> updateUserProfile(@RequestBody @Valid UpdateUserProfileRequest request) {
        ResponsePayload responsePayload = userService.updateUserProfile(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/hard")
    public ResponseEntity<ResponsePayload> hardDeleteUser(@RequestBody @Valid DeleteUserRequest request) {
        ResponsePayload responsePayload = userService.hardDeleteUser(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @DeleteMapping
    public ResponseEntity<ResponsePayload> softDeleteUser(@RequestBody @Valid DeleteUserRequest request) {
        ResponsePayload responsePayload = userService.softDeleteUser(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PatchMapping
    public ResponseEntity<ResponsePayload> softDeleteUser(@RequestBody @Valid RestoreUserRequest request) {
        ResponsePayload responsePayload = userService.restoreUserService(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
