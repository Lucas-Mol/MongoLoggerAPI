package com.mongologgerapi.repository;

import com.mongologgerapi.domain.model.Password;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends MongoRepository<Password, String> {
}
