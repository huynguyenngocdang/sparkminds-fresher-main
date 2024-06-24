package com.sparkminds.fresher_project_backend.mapper;

import com.sparkminds.fresher_project_backend.dto.request.CreateUserRequest;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserProfile;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "isDelete", constant = "false")
    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "userRoles", ignore = true)
    @Mapping(target = "products", ignore = true)
    User toUserFromCreateRequest(CreateUserRequest request);
    @Mapping(target = "isDelete", constant = "false")
    @Mapping(target = "user", ignore = true)
    UserProfile toUserProfileFromCreateRequest(CreateUserRequest request);
}
