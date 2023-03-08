package com.management.service.Impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.entity.RefreshToken;
import com.management.exception.TokenRefreshException;
import com.management.repository.RefreshTokenRepository;
import com.management.repository.UserRepository;
import com.management.service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
	@Value("${security.app.jwtRefreshExpiration}")
	private Long refreshTokenDuration;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken);
	}

	@Override
	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findById(userId).get());
		refreshToken.setExpiryDate(Instant.now().plusSeconds(refreshTokenDuration));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	@Override
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getRefreshToken(),
					"Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Override
	@Transactional
	public int deleteByUserId(Long userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	}
}
