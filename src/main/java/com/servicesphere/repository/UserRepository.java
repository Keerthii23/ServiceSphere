package com.servicesphere.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesphere.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}