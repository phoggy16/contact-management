package com.cms.cms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserContactsDto {

    @NotBlank(message = "firstName can't be null")
    private String firstName;

    private String lastName;

    @NotBlank(message = "email can't be null")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "phoneNumber can't be null")
    @Pattern(regexp = "^(\\+\\d{1,3})?[-.\\s]?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$",message="Enter valid phone number")
    private String phoneNumber;
}
