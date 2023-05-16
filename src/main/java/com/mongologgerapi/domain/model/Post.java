package com.mongologgerapi.domain.model;

import com.mongologgerapi.domain.dto.input.PostInputDTO;
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
@TypeAlias("post")
public class Post extends Log {

    private Long postId;

    public Post(PostInputDTO postDTO) {
        setTimestamp(LocalDateTime.now());
        setMethod(postDTO.method());
        setSuccess(postDTO.success());
        setMessage(postDTO.message());
        setUserId(postDTO.userId());
        this.postId = postDTO.postId();
    }
}
