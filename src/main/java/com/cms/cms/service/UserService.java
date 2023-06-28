package com.cms.cms.service;


import com.cms.cms.dto.UserRegistrationDto;

public interface UserService {
    String registerUser(UserRegistrationDto userRegistrationDto);
}
