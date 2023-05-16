package com.mongologgerapi.services;


import com.mongologgerapi.domain.model.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmailLogger extends Logger {

    Page<Email> getAll(Pageable pageable);

    Page<Email> getAllByUser(Pageable pageable, Long userId);

    Page<Email> getAllBySender(Pageable pageable, String sender);

    Page<Email> getAllByRecipient(Pageable pageable, String recipient);

    Page<Email> getAllByUserAndRecipient(Pageable pageable, Long userId, String recipient);

    Page<Email> getAllBySenderAndRecipient(Pageable pageable, String sender, String recipient);

    Page<Email> getAllByUserIdAndSenderAndRecipient(Pageable pageable, Long userId, String sender, String recipient);

}
