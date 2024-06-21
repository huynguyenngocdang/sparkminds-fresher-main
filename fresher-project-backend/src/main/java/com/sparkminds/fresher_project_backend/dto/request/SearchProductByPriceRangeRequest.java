package com.sparkminds.fresher_project_backend.dto.request;

import com.sparkminds.fresher_project_backend.constant.ProductSearchConstant;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductByPriceRangeRequest {
    @PositiveOrZero(message = ProductSearchConstant.INVALID_PRICE_MIN_NOT_POSITIVE)
    private Double priceMin;
    @PositiveOrZero(message = ProductSearchConstant.INVALID_PRICE_MAX_NOT_POSITIVE)
    private Double priceMax;
}
