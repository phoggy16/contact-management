package com.cms.cms.controller;

import com.cms.cms.dto.UserContactsDto;
import com.cms.cms.response.CustomResponse;
import com.cms.cms.service.UserContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/v1")
@RequiredArgsConstructor
public class UserContactController {

    private final UserContactService userContactService;

    @PostMapping("/contact")
    public ResponseEntity<CustomResponse> addContact(@Valid @RequestBody UserContactsDto userContactsDto) {
        userContactService.addUserContact(userContactsDto);
        return new CustomResponse("Contact added successfully", HttpStatus.CREATED).toResponseEntity();
    }

    @GetMapping("/contact")
    public ResponseEntity<CustomResponse> getContact(@RequestParam(required = false) String search) {
        return new CustomResponse("Contact fetched successfully", userContactService.getUserContacts(search), HttpStatus.OK).toResponseEntity();
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<CustomResponse> updateContact(@PathVariable Long id, @Valid @RequestBody UserContactsDto userContactsDto) {
        userContactService.updateUserContact(userContactsDto, id);
        return new CustomResponse("Contact updated successfully", HttpStatus.CREATED).toResponseEntity();
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<CustomResponse> deleteContact(@PathVariable Long id) {
        userContactService.deleteUserContact(id);
        return new CustomResponse("Contact deleted successfully", HttpStatus.OK).toResponseEntity();
    }
}
