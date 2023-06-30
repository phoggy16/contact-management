package com.cms.cms.service;

import com.cms.cms.dto.UserRegistrationDto;
import com.cms.cms.entity.UserEntity;
import com.cms.cms.exceptionhandling.CustomException;
import com.cms.cms.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User can not be found in database");
            throw new UsernameNotFoundException("Username not found");
        }

        Collection<SimpleGrantedAuthority> authorites = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorites);
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        UserEntity userEntity = UserEntity.builder().name(userRegistrationDto.getName())
                .password(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()))
                .username(userRegistrationDto.getUsername()).build();
        UserEntity user = userRepo.findByUsername(userEntity.getUsername());
        if (user != null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        userRepo.save(userEntity);
    }
}
