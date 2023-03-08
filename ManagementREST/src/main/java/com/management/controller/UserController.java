package com.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.payload.request.UserRequest;
import com.management.service.UserService;

@RestController
@RequestMapping("/api/rest/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.createUser(userRequest));

	}
}
