package com.sparkminds.fresher_project_backend.mapper;

import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.dto.request.LoginRequest;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "isDelete", constant = "false")
    @Mapping(target = "user", ignore = true)
    UserProfile toUserProfileFromCreateRequest(CreateUserRequest request);

}
