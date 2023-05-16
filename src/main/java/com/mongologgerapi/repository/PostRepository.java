package com.mongologgerapi.repository;

import com.mongologgerapi.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByUserId(Pageable pageable, Long userId);

    Page<Post> findAllByPostId(Pageable pageable, Long postId);

    Page<Post> findAllByUserIdAndPostId(Pageable pageable, Long userId, Long postId);

}
