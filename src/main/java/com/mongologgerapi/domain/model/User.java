package com.mongologgerapi.domain.model;

import com.mongologgerapi.domain.dto.input.UserInputDTO;
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
@TypeAlias("user")
public class User extends Log{

    private Long userId;
    private String ipAddress;

    public User(UserInputDTO userDTO) {
        setTimestamp(LocalDateTime.now());
        setMethod(userDTO.method());
        setSuccess(userDTO.success());
        setMessage(userDTO.message());
        this.userId = userDTO.userId();
        this.ipAddress = userDTO.ipAddress();
    }
}
