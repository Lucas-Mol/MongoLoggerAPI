package com.mongologgerapi.services;

import com.mongologgerapi.domain.exception.LogNotFoundException;
import com.mongologgerapi.domain.model.Email;
import com.mongologgerapi.domain.model.Log;
import com.mongologgerapi.domain.model.User;
import com.mongologgerapi.repository.EmailRepository;
import com.mongologgerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserLoggerService implements UserLogger {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Log log(Log log) {
        return userRepository.save((User) log);
    }

    @Override
    public Log getById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new LogNotFoundException("Log not found by id"));
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllByUser(Pageable pageable, Long userId) {
        return userRepository.findAllByUserId(pageable, userId);
    }

    @Override
    public Page<User> getAllByIpAddress(Pageable pageable, String ipAddress) {
        return userRepository.findAllByIpAddress(pageable, ipAddress);
    }

    @Override
    public Page<User> getAllByUserAndIpAddress(Pageable pageable, Long userId, String ipAddress) {
        return userRepository.findAllByUserIdAndIpAddress(pageable, userId, ipAddress);
    }

    public Page<User> get(Pageable pageable, String userId, String ipAddress) {
        if(StringUtils.hasText(userId) && StringUtils.hasText(ipAddress))
            return getAllByUserAndIpAddress(pageable, Long.parseLong(userId), ipAddress);
        if(StringUtils.hasText(userId))
            return getAllByUser(pageable, Long.parseLong(userId));
        if(StringUtils.hasText(ipAddress))
            return getAllByIpAddress(pageable, ipAddress);
        return getAll(pageable);
    }

}
