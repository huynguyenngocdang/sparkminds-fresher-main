package com.sparkminds.fresher_project_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
