package com.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.UserDetailsDTO;
import com.management.payload.request.UserRequest;
import com.management.service.RefreshTokenService;
import com.management.service.UserService;

@RestController
@RequestMapping("/api/rest/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	RefreshTokenService refreshTokenService;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.createUser(userRequest));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestParam Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("Delete user success");
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser() {
		UserDetailsDTO userDetails = (UserDetailsDTO) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Long userId = userDetails.getId();
		refreshTokenService.deleteByUserId(userId);
		return ResponseEntity.ok("Log out successful!");
	}
}
