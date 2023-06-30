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
    @Pattern(regexp = ".{7,}",message = "password length must be more than or equal to 7")
    @NotBlank(message = "password can't be null")
    private String password;
}
