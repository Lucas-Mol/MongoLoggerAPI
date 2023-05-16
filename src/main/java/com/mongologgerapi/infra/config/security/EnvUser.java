package com.mongologgerapi.infra.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class EnvUser {

    @Value("${env.var.username}")
    private String ENV_VAR_USERNAME;

    @Value("${env.var.password}")
    private String ENV_VAR_PASSWORD;

    public String getUsername() {
        return this.ENV_VAR_USERNAME;
    }

    public String getPassword() {
        return ENV_VAR_PASSWORD;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

}
