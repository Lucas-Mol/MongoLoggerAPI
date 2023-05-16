package com.mongologgerapi.domain.dto.input;

public record PostInputDTO(
        String method,
        boolean success,
        String message,
        Long userId,
        Long postId
        )
{ }
