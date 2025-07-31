package com.bitwarden.server.repository;

import com.bitwarden.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailIgnoreCase(String email);

    @Query("SELECT u.publicKey FROM User u WHERE u.id = :userId")
    Optional<String> findPublicKeyById(@Param("userId") UUID userId);

    @Query("SELECT u.accountRevisionDate FROM User u WHERE u.id = :userId")
    Optional<LocalDateTime> findAccountRevisionDateById(@Param("userId") UUID userId);

    boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.premium = true")
    long countPremiumUsers();

    @Query("SELECT u FROM User u WHERE u.premiumExpirationDate < :date AND u.premium = true")
    java.util.List<User> findExpiredPremiumUsers(@Param("date") LocalDateTime date);
}
