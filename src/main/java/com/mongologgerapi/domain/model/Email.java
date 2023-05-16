package com.mongologgerapi.domain.model;

import com.mongologgerapi.domain.dto.input.EmailInputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "logs")
@TypeAlias("email")
public class Email extends Log {

    private String sender;
    private String recipient;

    public Email(EmailInputDTO emailDTO) {
        setTimestamp(LocalDateTime.now());
        setMethod(emailDTO.method());
        setSuccess(emailDTO.success());
        setMessage(emailDTO.message());
        setUserId(emailDTO.userId());
        this.sender = emailDTO.sender();
        this.recipient = emailDTO.recipient();
    }
}
