package com.sparkminds.fresher_project_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparkminds.fresher_project_backend.constant.BrandValidationConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBrandRequest {
    @NotBlank(message = BrandValidationConstant.BRAND_NAME_NOT_BLANK)
    @NotNull(message = BrandValidationConstant.BRAND_NAME_NOT_EMPTY)
    @JsonProperty("name")
    private String name;
}
