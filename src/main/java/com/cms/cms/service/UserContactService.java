package com.cms.cms.service;

import com.cms.cms.dto.UserContactsDto;
import com.cms.cms.entity.UserContactEntity;

import java.util.List;

public interface UserContactService {
    void addUserContact(UserContactsDto userContactsDto);

    List<UserContactEntity> getUserContacts(String search);
}
