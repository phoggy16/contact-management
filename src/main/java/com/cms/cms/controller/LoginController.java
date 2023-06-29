package com.cms.cms.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cms.cms.response.CustomResponse;
import com.cms.cms.dto.LoginRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/external/v1")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public CustomResponse login(@RequestBody LoginRequestDto loginRequest, HttpServletRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        Algorithm algorithm=Algorithm.HMAC256("secretKey".getBytes());
        String access_token= JWT.create()
                .withSubject(loginRequest.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        String refresh_token= JWT.create()
                .withSubject(loginRequest.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Map<String,String> loginTokens=new HashMap<>();
        loginTokens.put("access_token",access_token);
        loginTokens.put("refresh_token",refresh_token);
        return new CustomResponse("Logged In Successfully",loginTokens, HttpStatus.OK);
    }
}
