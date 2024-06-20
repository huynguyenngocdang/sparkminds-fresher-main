package com.sparkminds.fresher_project_backend.dto.request;

import com.sparkminds.fresher_project_backend.constant.UserValidationConstant;
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
public class UpdateUserRequest {
    @NotBlank(message = UserValidationConstant.USERNAME_NOT_BLANK)
    @NotNull(message = UserValidationConstant.USERNAME_NOT_EMPTY)
    private String username;

    @NotBlank(message = UserValidationConstant.PASSWORD_NOT_BLANK)
    @NotNull(message = UserValidationConstant.PASSWORD_NOT_EMPTY)
    private String password;
}
