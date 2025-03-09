package com.ecommerce.domain.repository;

import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.valueobjects.Email;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository{
    Optional<User> findByEmail(Email email);
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
    List<User> findAll();
    User save(User user);
    void deleteById(UUID id);
}
