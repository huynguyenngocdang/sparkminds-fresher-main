package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserProfileRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateUserRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CreateUserService;
import com.sparkminds.fresher_project_backend.service.UpdateUserProfileService;
import com.sparkminds.fresher_project_backend.service.UpdateUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/create")
    public ResponseEntity<ResponsePayload> createNewUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        ResponsePayload responsePayload = createUserService.createUser(createUserRequest);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PostMapping("/update-user")
    public ResponseEntity<ResponsePayload> updateUser(@RequestBody @Valid UpdateUserRequest request) {
        ResponsePayload responsePayload = updateUserService.updateUser(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PostMapping("/update-profile")
    public ResponseEntity<ResponsePayload> updateUserProfile(@RequestBody @Valid UpdateUserProfileRequest request) {
        ResponsePayload responsePayload = updateUserProfileService.updateUserProfile(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
