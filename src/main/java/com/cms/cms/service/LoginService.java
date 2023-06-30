package com.cms.cms.service;

import com.cms.cms.dto.LoginRequestDto;
import com.cms.cms.dto.LoginResponseDTO;
import com.cms.cms.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    public LoginResponseDTO loginUser(LoginRequestDto loginRequest, HttpServletRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String requestURL = request.getRequestURL().toString();

        String accessToken = jwtUtil.generateJWTToken(loginRequest.getUsername(), requestURL, new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        String refreshToken = jwtUtil.generateJWTToken(loginRequest.getUsername(), requestURL, new Date(System.currentTimeMillis() + 1000 * 60 * 1000));

        return LoginResponseDTO.builder().accessToken(accessToken).build();
    }
}
