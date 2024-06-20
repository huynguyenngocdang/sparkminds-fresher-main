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

    private Long id;
    private String productName;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double price;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double quantity;
    @JsonProperty("isDelete")
    private boolean isDelete;

    private Long brandId;
    private Long categoryId;
    private Long userId;
}
