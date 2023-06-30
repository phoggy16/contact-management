package com.cms.cms.controller;

import com.cms.cms.response.CustomResponse;
import com.cms.cms.dto.LoginRequestDto;
import com.cms.cms.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/external/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@Valid @RequestBody LoginRequestDto loginRequest, HttpServletRequest request) {
        return new CustomResponse("Logged In Successfully",loginService.loginUser(loginRequest,request), HttpStatus.OK).toResponseEntity();
    }
}
