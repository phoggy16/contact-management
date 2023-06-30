package com.cms.cms.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegistrationDto {
    @NotBlank(message = "name can't be null")
    private String name;
    @NotBlank(message = "username can't be null")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\da-zA-Z]).{8,}$", message = "password must have a minimum length of 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character")
    @NotBlank(message = "password can't be null")
    private String password;
}
