package com.mongologgerapi.services;

import com.mongologgerapi.domain.exception.LogNotFoundException;
import com.mongologgerapi.domain.model.Email;
import com.mongologgerapi.domain.model.Log;
import com.mongologgerapi.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class EmailLoggerService implements EmailLogger {

    @Autowired
    private EmailRepository emailRepository;


    @Override
    public Log log(Log log) {
        return emailRepository.save((Email) log);
    }

    @Override
    public Log getById(String id) {
        return emailRepository.findById(id).orElseThrow(() -> new LogNotFoundException("Log not found by id"));
    }

    @Override
    public Page<Email> getAll(Pageable pageable) {
        return emailRepository.findAll(pageable);
    }

    @Override
    public Page<Email> getAllByUser(Pageable pageable, Long userId) {
        return emailRepository.findAllByUserId(pageable, userId);
    }

    @Override
    public Page<Email> getAllBySender(Pageable pageable, String sender) {
        return emailRepository.findAllBySender(pageable, sender);
    }

    @Override
    public Page<Email> getAllByRecipient(Pageable pageable, String recipient) {
        return emailRepository.findAllByRecipient(pageable, recipient);
    }

    @Override
    public Page<Email> getAllByUserAndRecipient(Pageable pageable, Long userId, String recipient) {
        return emailRepository.findAllByUserIdAndRecipient(pageable, userId, recipient);
    }

    @Override
    public Page<Email> getAllBySenderAndRecipient(Pageable pageable, String sender, String recipient) {
        return emailRepository.findAllBySenderAndRecipient(pageable, sender, recipient);
    }

    @Override
    public Page<Email> getAllByUserIdAndSenderAndRecipient(Pageable pageable, Long userId, String sender, String recipient) {
        return emailRepository.findAllByUserIdAndSenderAndRecipient(pageable, userId, sender, recipient);
    }

    public Page<Email> get(Pageable pageable, String userId, String sender, String recipient) {
        if(StringUtils.hasText(userId) && StringUtils.hasText(sender) && StringUtils.hasText(recipient))
            return getAllByUserIdAndSenderAndRecipient(pageable, Long.parseLong(userId), sender, recipient);
        if(StringUtils.hasText(userId) && StringUtils.hasText(recipient))
            return getAllByUserAndRecipient(pageable, Long.parseLong(userId), recipient);
        if(StringUtils.hasText(sender) && StringUtils.hasText(recipient))
            return getAllBySenderAndRecipient(pageable, sender, recipient);
        if(StringUtils.hasText(userId))
            return getAllByUser(pageable, Long.parseLong(userId));
        if(StringUtils.hasText(sender))
            return getAllBySender(pageable, sender);
        if(StringUtils.hasText(recipient))
            return getAllByRecipient(pageable, recipient);
        return getAll(pageable);
    }
}
