package com.mongologgerapi.services;


import com.mongologgerapi.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserLogger extends Logger {

    Page<User> getAll(Pageable pageable);

    Page<User> getAllByUser(Pageable pageable, Long userId);

    Page<User> getAllByIpAddress(Pageable pageable, String ipAddress);

    Page<User> getAllByUserAndIpAddress(Pageable pageable, Long userId,  String ipAddress);

}
