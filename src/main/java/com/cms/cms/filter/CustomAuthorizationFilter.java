package com.cms.cms.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("/external/")){
            filterChain.doFilter(request,response);
        }else{
            try{
                log.info("into the try block");
                String authorizationHeader=request.getHeader(AUTHORIZATION);
                log.info("Header is {}",authorizationHeader);
                if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
                    String token=authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm=Algorithm.HMAC256("secretKey".getBytes());
                    JWTVerifier verifier= JWT.require(algorithm).build();
                    DecodedJWT decodedJWT= verifier.verify(token);
                    String username= decodedJWT.getSubject();
                    String[] roles=decodedJWT.getClaim("roles").asArray(String.class);

                    Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();

                    stream(roles).forEach(role ->{
                        log.info("Role is {}",role);
                        authorities.add(new SimpleGrantedAuthority(role));
                    });

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                            new UsernamePasswordAuthenticationToken(username,null,authorities);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request,response);

                }else{
                    log.error("Into the else condition");
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    Map<String,String> error=new HashMap<>();
                    error.put("error_message","Please provide valid token");
//                token.put("refresh_token",refresh_token);

                    response.setContentType(APPLICATION_JSON_VALUE);

                    new ObjectMapper().writeValue(response.getOutputStream(),error);
                }
            }catch(Exception exception){
                log.error("Into the catch block");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                Map<String,String> error=new HashMap<>();
                error.put("error_message",exception.getMessage());
//                token.put("refresh_token",refresh_token);

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }

        }
    }
}
