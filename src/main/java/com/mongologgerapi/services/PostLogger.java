package com.mongologgerapi.services;


import com.mongologgerapi.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostLogger extends Logger {

    Page<Post> getAll(Pageable pageable);

    Page<Post> getAllByUser(Pageable pageable, Long userId);

    Page<Post> getAllByPost(Pageable pageable, Long postId);

    Page<Post> getAllByUserAndPost(Pageable pageable, Long userId, Long postId);

}
