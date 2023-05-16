package com.mongologgerapi.domain.dto.input;

public record UserInputDTO(
        String method,
        boolean success,
        String message,
        Long userId,
        String ipAddress
        )
{ }
