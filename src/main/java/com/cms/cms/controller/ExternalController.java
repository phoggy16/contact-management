package com.cms.cms.controller;

import com.cms.cms.dto.UserRegistrationDto;
import com.cms.cms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external/v1")
@RequiredArgsConstructor
public class ExternalController {

    private final UserService userService;
    @PostMapping("/registerUser")
    public String registerUser(@RequestBody UserRegistrationDto userRegistrationDto){
        return userService.registerUser(userRegistrationDto);
    }
}
