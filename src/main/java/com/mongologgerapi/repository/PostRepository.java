package com.mongologgerapi.repository;

import com.mongologgerapi.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ '_class': 'post'}")
    Page<Post> findAll(Pageable pageable);

    @Query("{ '_class': 'post', 'userId': ?0 }")
    Page<Post> findAllByUserId(Pageable pageable, Long userId);

    @Query("{ '_class': 'post', 'postId': ?0 }")
    Page<Post> findAllByPostId(Pageable pageable, Long postId);

    @Query("{ '_class': 'post', 'userId': ?0 , 'postId':  ?1}")
    Page<Post> findAllByUserIdAndPostId(Pageable pageable, Long userId, Long postId);

}
