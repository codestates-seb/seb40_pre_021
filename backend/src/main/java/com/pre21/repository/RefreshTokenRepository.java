package com.pre21.repository;

import com.pre21.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);

    Optional<RefreshToken> findRefreshTokenByTokenValue(String refreshToken);

    Optional<RefreshToken> findRefreshTokenByTokenEmail(String email);

    void deleteRefreshTokenByTokenEmail(String email);

    void deleteRefreshTokenByTokenValue(String refershToken);

    void deleteRefreshTokenByUserId(Long userId);
}
