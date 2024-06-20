package com.sparkminds.fresher_project_backend.dto.request;

import com.sparkminds.fresher_project_backend.constant.CommonValidationConstant;
import com.sparkminds.fresher_project_backend.constant.UserValidationConstant;
import jakarta.validation.constraints.Min;
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
public class UpdateUserProfileRequest {
    @NotBlank(message = UserValidationConstant.USERNAME_NOT_BLANK)
    @NotNull(message = UserValidationConstant.USERNAME_NOT_EMPTY)
    private String username;

    @NotNull(message = UserValidationConstant.USER_FULL_NAME_NOT_EMPTY)
    private String fullName;

    @NotNull(message = UserValidationConstant.USER_AGE_NOT_EMPTY)
    @Min(value = CommonValidationConstant.VALID_USER_AGE, message = UserValidationConstant.USER_AGE_VALID)
    private int age;

    @NotNull(message = UserValidationConstant.USER_GENDER_NOT_EMPTY)
    private String gender;

}
