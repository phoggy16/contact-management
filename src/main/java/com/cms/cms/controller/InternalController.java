package com.cms.cms.controller;

import com.cms.cms.dto.UserContactsDto;
import com.cms.cms.response.CustomResponse;
import com.cms.cms.service.UserContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/v1")
@RequiredArgsConstructor
public class InternalController {

    private final UserContactService userContactService;

    @PostMapping("/contact")
    public CustomResponse addContact(@Valid @RequestBody UserContactsDto userContactsDto) {
        userContactService.addUserContact(userContactsDto);
        return new CustomResponse("Contact added successfully", null, HttpStatus.CREATED);
    }

    @GetMapping("/contact")
    public CustomResponse getContact(@RequestParam(required = false) String search){
        return new CustomResponse("Contact added successfully", userContactService.getUserContacts(search), HttpStatus.CREATED);
    }
}
