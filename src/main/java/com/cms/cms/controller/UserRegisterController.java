package com.cms.cms.controller;

import com.cms.cms.dto.UserRegistrationDto;
import com.cms.cms.response.CustomResponse;
import com.cms.cms.service.UserService;
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
public class UserRegisterController {

    private final UserService userService;
    @PostMapping("/registerUser")
    public ResponseEntity<CustomResponse> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto){
        userService.registerUser(userRegistrationDto);
        return new CustomResponse("User registered successfully", HttpStatus.CREATED).toResponseEntity();
    }
}
