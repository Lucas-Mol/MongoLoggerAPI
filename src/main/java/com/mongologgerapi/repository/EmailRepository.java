package com.mongologgerapi.repository;

import com.mongologgerapi.domain.model.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

    Page<Email> findAll(Pageable pageable);

    Page<Email> findAllByUserId(Pageable pageable, Long userId);

    Page<Email> findAllBySender(Pageable pageable, String sender);

    Page<Email> findAllByRecipient(Pageable pageable, String recipient);

    Page<Email> findAllByUserIdAndRecipient(Pageable pageable, Long userId, String recipient);

    Page<Email> findAllBySenderAndRecipient(Pageable pageable, String sender, String recipient);

    Page<Email> findAllByUserIdAndSenderAndRecipient(Pageable pageable, Long userId, String sender, String recipient);

}
