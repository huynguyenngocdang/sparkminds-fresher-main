package com.sparkminds.fresher_project_backend.dto.request;

import com.sparkminds.fresher_project_backend.constant.BrandValidationConstant;
import com.sparkminds.fresher_project_backend.constant.CategoryValidationConstant;
import com.sparkminds.fresher_project_backend.constant.ProductValidationConstant;
import com.sparkminds.fresher_project_backend.constant.UserValidationConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest {
    @NotBlank(message = UserValidationConstant.USERNAME_NOT_BLANK)
    @NotNull(message = UserValidationConstant.USERNAME_NOT_EMPTY)
    private String username;

    @NotBlank(message = BrandValidationConstant.BRAND_NOT_BLANK)
    @NotNull(message = BrandValidationConstant.BRAND_NOT_EMPTY)
    private String brandName;

    @NotBlank(message = CategoryValidationConstant.CATEGORY_NOT_BLANK)
    @NotNull(message = CategoryValidationConstant.CATEGORY_NOT_EMPTY)
    private String categoryName;

    @NotBlank(message = ProductValidationConstant.PRODUCT_NAME_NOT_BLANK)
    @NotNull(message = ProductValidationConstant.PRODUCT_NAME_NOT_EMPTY)
    private String productName;

    @NotNull(message = ProductValidationConstant.PRODUCT_PRICE_NOT_EMPTY)
    @Positive(message = ProductValidationConstant.PRODUCT_PRICE_NOT_NEGATIVE)
    private Double price;

    @NotNull(message = ProductValidationConstant.PRODUCT_QUANTITY_NOT_EMPTY)
    @Positive(message = ProductValidationConstant.PRODUCT_QUANTITY_NOT_NEGATIVE)
    private Double quantity;
}
