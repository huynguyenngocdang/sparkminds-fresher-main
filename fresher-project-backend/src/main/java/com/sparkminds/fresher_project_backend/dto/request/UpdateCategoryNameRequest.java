package com.sparkminds.fresher_project_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparkminds.fresher_project_backend.constant.CategoryValidationConstant;
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
public class UpdateCategoryNameRequest {
    @NotNull(message = CategoryValidationConstant.CATEGORY_ID_NOT_EMPTY)
    @JsonProperty("categoryId")
    private Long categoryId;

    @NotBlank(message = CategoryValidationConstant.CATEGORY_NAME_NOT_BLANK)
    @NotNull(message = CategoryValidationConstant.CATEGORY_NAME_NOT_EMPTY)
    @JsonProperty("categoryNewName")
    private String categoryNewName;
}
