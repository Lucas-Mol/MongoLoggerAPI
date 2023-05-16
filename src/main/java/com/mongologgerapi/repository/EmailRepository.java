package com.mongologgerapi.repository;

import com.mongologgerapi.domain.model.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

    @Query("{ '_class': 'email'}")
    Page<Email> findAll(Pageable pageable);

    @Query("{ '_class': 'email', 'userId': ?0 }")
    Page<Email> findAllByUserId(Pageable pageable, Long userId);

    @Query("{ '_class': 'email', 'sender': ?0 }")
    Page<Email> findAllBySender(Pageable pageable, String sender);

    @Query("{ '_class': 'email', 'recipient': ?0 }")
    Page<Email> findAllByRecipient(Pageable pageable, String recipient);

    @Query("{ '_class': 'email', 'userId': ?0 , 'recipient':  ?1}")
    Page<Email> findAllByUserIdAndRecipient(Pageable pageable, Long userId, String recipient);

    @Query("{ '_class': 'email', 'sender': ?0 , 'recipient':  ?1}")
    Page<Email> findAllBySenderAndRecipient(Pageable pageable, String sender, String recipient);

    @Query("{ '_class': 'email', 'userId': ?0 , 'sender':  ?1, 'recipient':  ?2}")
    Page<Email> findAllByUserIdAndSenderAndRecipient(Pageable pageable, Long userId, String sender, String recipient);

}
