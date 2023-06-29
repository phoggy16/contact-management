package com.cms.cms.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class CustomResponse {
    private  String message;
    private  Object data;
    private HttpStatus httpStatus;
}
