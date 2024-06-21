package com.sparkminds.fresher_project_backend.dto.request;

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
public class UpdateBrandNameRequest {
    @NotNull(message = BrandValidationConstant.BRAND_ID_NOT_EMPTY)
    private Long brandId;

    @NotBlank(message = BrandValidationConstant.BRAND_NAME_NOT_BLANK)
    @NotNull(message = BrandValidationConstant.BRAND_NAME_NOT_EMPTY)
    private String brandNewName;
}
