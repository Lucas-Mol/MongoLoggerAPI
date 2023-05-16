package com.mongologgerapi.services;

import com.mongologgerapi.domain.exception.LogNotFoundException;
import com.mongologgerapi.domain.model.Email;
import com.mongologgerapi.domain.model.Log;
import com.mongologgerapi.repository.EmailRepository;
import com.mongologgerapi.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;


@Service
public class LoggerService implements Logger {

    @Autowired
    private LogRepository logRepository;


    @Override
    public Log log(Log log) {
        return null;
    }

    @Override
    public Log getById(String id) {
        return null;
    }

    public Page<Log> getAll(Pageable pageable) {
        return logRepository.findAll(pageable);
    }

    public Page<Log> getAllByUser(Pageable pageable, Long UserId) {
        return logRepository.findAllByUserId(pageable, UserId);
    }

    public Page<Log> getAllByMethod(Pageable pageable, String method) {
        return logRepository.findAllByMethodContainingIgnoreCase(pageable, method);
    }

    public Page<Log> getAllByTimestampBetween(Pageable pageable, LocalDateTime from, LocalDateTime to) {
        return logRepository.findAllByTimestampBetween(pageable, from, to);
    }

    public Page<Log> getAllByUserIdAndMethodContainingIgnoreCase(Pageable pageable, Long userId, String method) {
        return logRepository.findAllByUserIdAndMethodContainingIgnoreCase(pageable, userId, method);
    }

    public Page<Log> getAllByUserIdAndTimestampBetween(Pageable pageable,
                                                       Long userId,
                                                       LocalDateTime from,
                                                       LocalDateTime to) {
        return logRepository.findAllByUserIdAndTimestampBetween(pageable, userId, from, to);
    }

    public Page<Log> getAllByMethodContainingIgnoreCaseAndTimestampBetween(Pageable pageable,
                                                                           String method,
                                                                           LocalDateTime from,
                                                                           LocalDateTime to) {
        return logRepository.findAllByMethodContainingIgnoreCaseAndTimestampBetween(pageable,method, from, to);
    }

    public Page<Log> getAllByUserIdAndMethodContainingIgnoreCaseAndTimestampBetween(Pageable pageable,
                                                                                    Long userId,
                                                                                    String method,
                                                                                    LocalDateTime from,
                                                                                    LocalDateTime to) {
        return logRepository.findAllByUserIdAndMethodContainingIgnoreCaseAndTimestampBetween(pageable, userId, method, from, to);
    }


    public Page<Log> get(Pageable pageable, String userId, String method, LocalDateTime from, LocalDateTime to) {
        boolean hasFromAndTo = from != null && to != null;

        if(StringUtils.hasText(userId) && StringUtils.hasText(method) && hasFromAndTo)
            return getAllByUserIdAndMethodContainingIgnoreCaseAndTimestampBetween
                    (pageable, Long.parseLong(userId), method, from, to);
        if(StringUtils.hasText(method) && hasFromAndTo)
            return getAllByMethodContainingIgnoreCaseAndTimestampBetween(pageable, method, from, to);
        if(StringUtils.hasText(userId) && hasFromAndTo)
            return getAllByUserIdAndTimestampBetween(pageable, Long.parseLong(userId), from, to);
        if(StringUtils.hasText(userId) && StringUtils.hasText(method))
            return getAllByUserIdAndMethodContainingIgnoreCase(pageable, Long.parseLong(userId), method);
        if(hasFromAndTo)
            return getAllByTimestampBetween(pageable, from, to);
        if(StringUtils.hasText(method))
            return getAllByMethod(pageable, method);
        if(StringUtils.hasText(userId))
            return getAllByUser(pageable, Long.parseLong(userId));
        return getAll(pageable);
    }
}
