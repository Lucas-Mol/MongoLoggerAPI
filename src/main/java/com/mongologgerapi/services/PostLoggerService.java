package com.mongologgerapi.services;

import com.mongologgerapi.domain.exception.LogNotFoundException;
import com.mongologgerapi.domain.model.Log;
import com.mongologgerapi.domain.model.Post;
import com.mongologgerapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class PostLoggerService implements PostLogger {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Log log(Log log) {
        return postRepository.save((Post) log);
    }

    @Override
    public Log getById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new LogNotFoundException("Log not found by id"));
    }

    @Override
    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> getAllByUser(Pageable pageable, Long userId) {
        return postRepository.findAllByUserId(pageable, userId);
    }

    @Override
    public Page<Post> getAllByPost(Pageable pageable, Long postId) {
        return postRepository.findAllByPostId(pageable, postId);
    }

    @Override
    public Page<Post> getAllByUserAndPost(Pageable pageable, Long userId, Long postId) {
        return postRepository.findAllByUserIdAndPostId(pageable, userId, postId);
    }

    public Page<Post> get(Pageable pageable, String userId, String postId) {
        if(StringUtils.hasText(userId) && StringUtils.hasText(postId))
            return getAllByUserAndPost(pageable, Long.parseLong(userId), Long.parseLong(postId));
        if(StringUtils.hasText(userId))
            return getAllByUser(pageable, Long.parseLong(userId));
        if(StringUtils.hasText(postId))
            return getAllByPost(pageable, Long.parseLong(postId));
        return getAll(pageable);
    }
}
