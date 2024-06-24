package com.sparkminds.fresher_project_backend.mapper;


import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.dto.response.SearchProductResponse;
import com.sparkminds.fresher_project_backend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "category", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "isDelete", constant = "false")
    })
    Product createRequestToProduct(CreateProductRequest request);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "user.id", target = "userId")
    SearchProductResponse toSearchProductResponse(Product product);

    List<SearchProductResponse> toSearchProductResponseList(List<Product> products);
}
