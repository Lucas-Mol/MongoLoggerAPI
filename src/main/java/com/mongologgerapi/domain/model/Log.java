package com.mongologgerapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "logs")
@TypeAlias("log")
public abstract class Log {

    @Id
    private String id;
    private LocalDateTime timestamp;
    private String method;
    private boolean success;
    private String message;
    private Long userId;

}
