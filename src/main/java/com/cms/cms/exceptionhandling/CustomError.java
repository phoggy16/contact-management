package com.cms.cms.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomError {
    private HttpStatus status;
    private String message;
    private Instant timestamp;
}
