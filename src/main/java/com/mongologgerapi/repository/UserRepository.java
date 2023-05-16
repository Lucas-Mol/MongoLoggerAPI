package com.mongologgerapi.repository;

import com.mongologgerapi.domain.model.Email;
import com.mongologgerapi.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ '_class': 'user'}")
    Page<User> findAll(Pageable pageable);

    @Query("{ '_class': 'user', 'userId': ?0 }")
    Page<User> findAllByUserId(Pageable pageable, Long userId);

    @Query("{ '_class': 'user', 'ipAddress': ?0 }")
    Page<User> findAllByIpAddress(Pageable pageable, String ipAddress);

    @Query("{ '_class': 'user', 'userId': ?0 , 'ipAddress':  ?1}")
    Page<User> findAllByUserIdAndIpAddress(Pageable pageable, Long userId, String ipAddress);
}
