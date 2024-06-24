package com.sparkminds.fresher_project_backend.mapper;

import com.sparkminds.fresher_project_backend.dto.response.RoleResponse;
import com.sparkminds.fresher_project_backend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toRoleResponseFromRole(Role role);
}
