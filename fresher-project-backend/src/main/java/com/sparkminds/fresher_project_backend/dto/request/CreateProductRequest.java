package com.sparkminds.fresher_project_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparkminds.fresher_project_backend.constant.BrandValidationConstant;
import com.sparkminds.fresher_project_backend.constant.CategoryValidationConstant;
import com.sparkminds.fresher_project_backend.constant.ProductValidationConstant;
import com.sparkminds.fresher_project_backend.constant.UserValidationConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest {

    @NotNull(message = UserValidationConstant.USER_ID_NOT_EMPTY)
    @JsonProperty("userId")
    private Long userId;


    @NotNull(message = BrandValidationConstant.BRAND_ID_NOT_EMPTY)
    @JsonProperty("brandId")
    private Long brandId;

    @NotNull(message = CategoryValidationConstant.CATEGORY_ID_NOT_EMPTY)
    @JsonProperty("categoryId")
    private Long categoryId;

    @NotBlank(message = ProductValidationConstant.PRODUCT_NAME_NOT_BLANK)
    @NotNull(message = ProductValidationConstant.PRODUCT_NAME_NOT_EMPTY)
    @JsonProperty("productName")
    private String productName;

    @NotNull(message = ProductValidationConstant.PRODUCT_PRICE_NOT_EMPTY)
    @PositiveOrZero(message = ProductValidationConstant.PRODUCT_PRICE_NOT_NEGATIVE)
    @JsonProperty("price")
    private Double price;

    @NotNull(message = ProductValidationConstant.PRODUCT_QUANTITY_NOT_EMPTY)
    @PositiveOrZero(message = ProductValidationConstant.PRODUCT_QUANTITY_NOT_NEGATIVE)
    @JsonProperty("quantity")
    private Double quantity;
}
