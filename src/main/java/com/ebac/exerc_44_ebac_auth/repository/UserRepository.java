package com.ebac.exerc_44_ebac_auth.repository;

import com.ebac.exerc_44_ebac_auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
