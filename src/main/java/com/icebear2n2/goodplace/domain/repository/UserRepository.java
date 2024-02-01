package com.icebear2n2.goodplace.domain.repository;

import com.icebear2n2.goodplace.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findByProviderAndProviderUserId(String provider, String providerId);
    User findByNickname(String nickname);
    boolean existsByEmail(String email);
    User findByEmail(String email);
    void deleteByDeletedAtBefore(Timestamp threshold);
}