package com.sparkminds.fresher_project_backend.dto.request;

import com.sparkminds.fresher_project_backend.constant.CategoryValidationConstant;
import com.sparkminds.fresher_project_backend.constant.ProductConstant;
import com.sparkminds.fresher_project_backend.constant.ProductValidationConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDetailsRequest {
    @NotNull(message = ProductValidationConstant.PRODUCT_ID_NOT_EMPTY)
    private Long productId;
    @NotBlank(message = ProductValidationConstant.PRODUCT_NAME_NOT_BLANK)
    @NotNull(message = ProductValidationConstant.PRODUCT_NAME_NOT_EMPTY)
    private String productName;
    @NotNull(message = ProductValidationConstant.PRODUCT_PRICE_NOT_EMPTY)
    @PositiveOrZero(message = ProductValidationConstant.PRODUCT_PRICE_NOT_NEGATIVE)
    private Double price;
    @NotNull(message = ProductValidationConstant.PRODUCT_QUANTITY_NOT_EMPTY)
    @PositiveOrZero(message = ProductValidationConstant.PRODUCT_QUANTITY_NOT_NEGATIVE)
    private Double quantity;

}
