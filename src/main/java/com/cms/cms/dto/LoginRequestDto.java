package com.cms.cms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginRequestDto {
    @NotBlank(message = "username can't be null")
    String username;
    @NotBlank(message = "password can't be null")
    String password;
}
