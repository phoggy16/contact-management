package com.cms.cms.service;

import com.cms.cms.dto.UserContactResponseDto;
import com.cms.cms.dto.UserContactsDto;
import com.cms.cms.entity.UserContactEntity;
import com.cms.cms.entity.UserEntity;
import com.cms.cms.exceptionhandling.CustomException;
import com.cms.cms.repository.UserContactRepository;
import com.cms.cms.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<UserContactResponseDto> getUserContacts(String search) {
        UserEntity user=getCurrentLoggedInUserEntity();
        if(StringUtils.isEmpty(search)){
            return userContactRepository.findAllByUserId(user);
        }
        search=search.trim();
        return userContactRepository.findAllByUserIdBasedOnSearch(user,search);
    }

    @Override
    public void updateUserContact(UserContactsDto userContactsDto,Long contactId) {
        UserContactEntity userContactUpdated=userContactByIdAndUser(contactId);
        userContactUpdated.setEmail(userContactsDto.getEmail());
        userContactUpdated.setPhoneNumber(userContactsDto.getPhoneNumber());
        userContactUpdated.setFirstName(userContactsDto.getFirstName());
        userContactUpdated.setLastName(userContactsDto.getLastName());
        userContactRepository.save(userContactUpdated);
    }

    @Override
    public void deleteUserContact(Long contactId) {
        UserContactEntity userContact=userContactByIdAndUser(contactId);
        userContactRepository.delete(userContact);
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

    public UserContactEntity userContactByIdAndUser(Long contactId){
        UserEntity user=getCurrentLoggedInUserEntity();
        Optional<UserContactEntity> userContact=userContactRepository.findByIdAndUser(contactId,user);
        if(userContact.isPresent()){
           return userContact.get();
        }else{
            throw new CustomException(HttpStatus.BAD_REQUEST,"Contact id does not exist");
        }
    }
}
