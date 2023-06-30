package com.cms.cms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {
    private String message;
    private Object data;
    private HttpStatus httpStatus;

    public CustomResponse(String message, Object data, HttpStatus httpStatus) {
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public CustomResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ResponseEntity<CustomResponse> toResponseEntity() {
        return ResponseEntity.status(this.httpStatus).body(this);
    }

}
