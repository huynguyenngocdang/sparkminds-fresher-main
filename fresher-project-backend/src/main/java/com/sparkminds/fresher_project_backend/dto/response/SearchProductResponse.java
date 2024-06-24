package com.sparkminds.fresher_project_backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sparkminds.fresher_project_backend.utility.impl.CustomDoubleSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("productName")
    private String productName;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    @JsonProperty("price")
    private Double price;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    @JsonProperty("quantity")
    private Double quantity;
    @JsonProperty("isDelete")
    private boolean isDelete;
    @JsonProperty("brandId")
    private Long brandId;
    @JsonProperty("categoryId")
    private Long categoryId;
    @JsonProperty("userId")
    private Long userId;
}
