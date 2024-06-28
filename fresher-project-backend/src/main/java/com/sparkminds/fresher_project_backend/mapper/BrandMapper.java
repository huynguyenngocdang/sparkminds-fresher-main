package com.sparkminds.fresher_project_backend.mapper;

import com.sparkminds.fresher_project_backend.dto.response.BrandResponse;
import com.sparkminds.fresher_project_backend.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @Mapping(target = "isDelete", constant = "false")
    BrandResponse toBrandResponse(Brand brand);
}
