package com.management.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.entity.Token;
import com.management.repository.TokenRepository;
import com.management.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	TokenRepository tokenRepository;

	@Override
	public String newToken(Token token) {
		return tokenRepository.save(token).getToken();
	}

}
