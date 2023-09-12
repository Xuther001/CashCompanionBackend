package com.app.chat.common.security.repository;

import com.app.chat.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository <User, Integer> {

    Optional<User> findByEmail(String email);
}
