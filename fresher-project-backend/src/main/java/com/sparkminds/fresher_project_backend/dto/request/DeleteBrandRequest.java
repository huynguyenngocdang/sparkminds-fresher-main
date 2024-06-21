package com.sparkminds.fresher_project_backend.dto.request;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
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
public class DeleteBrandRequest {
    @NotNull(message = BrandValidationConstant.BRAND_ID_NOT_EMPTY)
    private Long brandId;
}
