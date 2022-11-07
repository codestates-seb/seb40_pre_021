package com.pre21.repository;

import com.pre21.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);
}
