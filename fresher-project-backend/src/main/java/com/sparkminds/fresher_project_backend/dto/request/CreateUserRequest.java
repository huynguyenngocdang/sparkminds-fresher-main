package com.sparkminds.fresher_project_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparkminds.fresher_project_backend.constant.CommonConstant;
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
public class CreateUserRequest {

    @NotBlank(message = UserValidationConstant.USERNAME_NOT_BLANK)
    @NotNull(message = UserValidationConstant.USERNAME_NOT_EMPTY)
    @JsonProperty("username")
    private String username;

    @NotBlank(message = UserValidationConstant.PASSWORD_NOT_BLANK)
    @NotNull(message = UserValidationConstant.PASSWORD_NOT_EMPTY)
    @JsonProperty("password")
    private String password;

    @NotNull(message = UserValidationConstant.USER_FULL_NAME_NOT_EMPTY)
    @JsonProperty("fullName")
    private String fullName;

    @NotNull(message = UserValidationConstant.USER_AGE_NOT_EMPTY)
    @Min(value = CommonValidationConstant.VALID_USER_AGE, message = UserValidationConstant.USER_AGE_VALID)
    @JsonProperty("age")
    private int age;

    @NotNull(message = UserValidationConstant.USER_GENDER_NOT_EMPTY)
    @JsonProperty("gender")
    private String gender;
}
