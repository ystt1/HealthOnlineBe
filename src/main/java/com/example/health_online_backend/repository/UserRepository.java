package com.example.health_online_backend.repository;

import com.example.health_online_backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findUserByEmail(String email);
    User findUserByFullName(String email);
    User findUserById(String id);
}
