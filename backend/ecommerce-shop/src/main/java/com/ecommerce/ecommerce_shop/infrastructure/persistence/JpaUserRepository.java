package com.ecommerce.ecommerce_shop.infrastructure.persistence;

import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID>, UserRepository {
}
