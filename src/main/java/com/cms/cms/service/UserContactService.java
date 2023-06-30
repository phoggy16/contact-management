package com.cms.cms.service;

import com.cms.cms.dto.UserContactResponseDto;
import com.cms.cms.dto.UserContactsDto;

import java.util.List;

public interface UserContactService {
    void addUserContact(UserContactsDto userContactsDto);

    List<UserContactResponseDto> getUserContacts(String search);

    void updateUserContact(UserContactsDto userContactsDto, Long contactId);

    void deleteUserContact(Long contactId);
}
