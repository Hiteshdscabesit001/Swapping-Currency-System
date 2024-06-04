package com.swappingcurrency.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Sign Up Details", description = "It contains details for user signup")
public class SignUpDto {

    @Schema(description = "It contains user's full name.")
    @NotEmpty(message = "Full name is required")
    @Size(min = 3)
    private String fullName;
    @Schema(description = "It contains email id")
    @NotEmpty(message = "Email is required")
    @Email(message = "Provide valid email id")
    private String email;
    @Schema(description = "Password for user signup.")
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    @Schema(description = "Confirm password for user signup.")
    @NotEmpty(message = "Confirm password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String confirmPassword;
}
