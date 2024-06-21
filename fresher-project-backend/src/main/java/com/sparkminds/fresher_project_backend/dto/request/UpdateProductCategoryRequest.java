package com.sparkminds.fresher_project_backend.dto.request;

import com.sparkminds.fresher_project_backend.constant.CategoryValidationConstant;
import com.sparkminds.fresher_project_backend.constant.ProductValidationConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductCategoryRequest {

    @NotNull(message = ProductValidationConstant.PRODUCT_ID_NOT_EMPTY)
    private Long productId;

    @NotNull(message = CategoryValidationConstant.CATEGORY_ID_NOT_EMPTY)
    private Long newCategoryId;
}
