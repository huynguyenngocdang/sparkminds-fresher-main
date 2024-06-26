package com.sparkminds.fresher_project_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparkminds.fresher_project_backend.annotation.ComparisonType;
import com.sparkminds.fresher_project_backend.annotation.FieldComparison;
import com.sparkminds.fresher_project_backend.constant.ProductSearchConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldComparison(firstField = "minPrice", secondField = "maxPrice",
comparisonType = ComparisonType.LESS_THAN_OR_EQUAL,
message = ProductSearchConstant.MIN_PRICE_LESS_MAX_PRICE)
public class SearchProductsRequest {
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("category")
    private String category;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("minPrice")
    private Double minPrice;
    @JsonProperty("maxPrice")
    private Double maxPrice;
}
