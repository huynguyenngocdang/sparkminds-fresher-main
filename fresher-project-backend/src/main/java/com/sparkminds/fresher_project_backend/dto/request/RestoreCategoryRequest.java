package com.sparkminds.fresher_project_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparkminds.fresher_project_backend.constant.CategoryValidationConstant;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestoreCategoryRequest {
    @NotNull(message = CategoryValidationConstant.CATEGORY_ID_NOT_EMPTY)
    @JsonProperty("categoryId")
    private Long categoryId;
}
