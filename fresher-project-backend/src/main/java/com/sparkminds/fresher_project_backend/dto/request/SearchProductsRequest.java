package com.sparkminds.fresher_project_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductsRequest {
    private String productName;
    private String category;
    private String brand;
    private Double minPrice;
    private Double maxPrice;
}
