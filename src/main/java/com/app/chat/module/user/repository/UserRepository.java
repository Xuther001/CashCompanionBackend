package com.app.chat.module.user.repository;

import com.app.chat.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
