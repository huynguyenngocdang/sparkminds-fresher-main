package com.sparkminds.fresher_project_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparkminds.fresher_project_backend.constant.BrandValidationConstant;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestoreBrandRequest {
    @NotNull(message = BrandValidationConstant.BRAND_ID_NOT_EMPTY)
    @JsonProperty("brandId")
    private Long brandId;
}
