package com.mongologgerapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "logs")
@TypeAlias("password")
public class Password {

    private Long userId;
    private String ipAddress;

}
