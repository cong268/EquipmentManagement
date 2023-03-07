package com.management.controller;

import java.time.Instant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.UserDetailsDTO;
import com.management.entity.Token;
import com.management.entity.User;
import com.management.request.LoginRequest;
import com.management.security.JwtUtils;
import com.management.service.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsDTO userDetails = (UserDetailsDTO) authentication.getPrincipal();

		String jwt = jwtUtils.generateJwtToken(userDetails);
		Token toke = Token.builder()
				.userId(userDetails.getId())
				.token(jwt)
				.expiryDate(Instant.now().plusSeconds(60))
				.build();
		return ResponseEntity.ok(tokenService.newToken(toke));

	}
}
