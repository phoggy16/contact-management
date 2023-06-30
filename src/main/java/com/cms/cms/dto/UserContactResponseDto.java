package com.cms.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserContactResponseDto {
    private Long id;
    
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}

