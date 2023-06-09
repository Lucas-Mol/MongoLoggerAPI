package com.mongologgerapi.domain.dto.input;

public record EmailInputDTO(
        String method,
        boolean success,
        String message,
        Long userId,
        String sender,
        String recipient
        )
{ }
