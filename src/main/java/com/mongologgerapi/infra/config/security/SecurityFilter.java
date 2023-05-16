package com.mongologgerapi.infra.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EnvUser envUser;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenJWT = getJWTToken(request);

        if(tokenJWT != null) {
            String subjectUsername = tokenService.validateAndGetSubject(tokenJWT);

            if(envUser.getUsername().equals(subjectUsername)) {
                UserDetails user = new User(envUser.getUsername(), envUser.getPassword(), new ArrayList<>());
                var authenticated = new UsernamePasswordAuthenticationToken(user, null, envUser.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticated);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null)
            return authHeader.replace("Bearer ", "");

        return null;
    }
}
