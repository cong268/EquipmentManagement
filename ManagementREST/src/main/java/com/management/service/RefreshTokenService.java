package com.management.service;

import java.util.Optional;

import com.management.entity.RefreshToken;

public interface RefreshTokenService {
	Optional<RefreshToken> findByRefreshToken(String refreshToken);

	RefreshToken createRefreshToken(Long userId);

	RefreshToken verifyExpiration(RefreshToken token);

	int deleteByUserId(Long userId);
}
