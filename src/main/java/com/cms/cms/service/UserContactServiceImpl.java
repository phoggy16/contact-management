package com.cms.cms.service;

import com.cms.cms.dto.UserContactsDto;
import com.cms.cms.entity.UserContactEntity;
import com.cms.cms.entity.UserEntity;
import com.cms.cms.exceptionhandling.CustomException;
import com.cms.cms.repository.UserContactRepository;
import com.cms.cms.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserContactServiceImpl implements UserContactService{

    private final UserContactRepository userContactRepository;
    private final UserRepository userRepository;

    @Override
    public void addUserContact(UserContactsDto userContactsDto) {
        UserEntity user=getCurrentLoggedInUserEntity();
        UserContactEntity userContact=UserContactEntity.builder()
                .email(userContactsDto.getEmail())
                .firstName(userContactsDto.getFirstName())
                .lastName(userContactsDto.getLastName())
                .phoneNumber(userContactsDto.getPhoneNumber())
                .user(user).build();

        userContactRepository.save(userContact);
    }

    @Override
    public List<UserContactEntity> getUserContacts(String search) {
        UserEntity user=getCurrentLoggedInUserEntity();
        return userContactRepository.findAllByUserId(user,search);
    }

    public UserEntity getCurrentLoggedInUserEntity(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user=userRepository.findByUsername(userName);
        if(user==null){
            throw new CustomException(HttpStatus.FORBIDDEN,"You don't have access");
        }
        return userRepository.findByUsername(userName);
    }
}
