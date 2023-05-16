package com.mongologgerapi.repository;

import com.mongologgerapi.domain.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;

public interface LogRepository extends MongoRepository<Log, String> {

    Page<Log> findAll(Pageable pageable);

    Page<Log> findAllByUserId(Pageable pageable, Long userId);

    Page<Log> findAllByMethodContainingIgnoreCase(Pageable pageable, String method);

    Page<Log> findAllByTimestampBetween(Pageable pageable, LocalDateTime from, LocalDateTime to);





    Page<Log> findAllByUserIdAndMethodContainingIgnoreCase(Pageable pageable, Long userId, String method);

    Page<Log> findAllByUserIdAndTimestampBetween(Pageable pageable,
                                                 Long userId,
                                                 LocalDateTime from,
                                                 LocalDateTime to);

    Page<Log> findAllByMethodContainingIgnoreCaseAndTimestampBetween(Pageable pageable,
                                                                     String method,
                                                                     LocalDateTime from,
                                                                     LocalDateTime to);

    Page<Log> findAllByUserIdAndMethodContainingIgnoreCaseAndTimestampBetween(Pageable pageable,
                                                                              Long userId,
                                                                              String method,
                                                                              LocalDateTime from,
                                                                              LocalDateTime to);
}
