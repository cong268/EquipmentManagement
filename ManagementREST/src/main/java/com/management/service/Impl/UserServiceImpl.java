package com.management.service.Impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.entity.Role;
import com.management.entity.User;
import com.management.payload.request.UserRequest;
import com.management.repository.RoleRepository;
import com.management.repository.UserRepository;
import com.management.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public Long createUser(UserRequest userRequest) {
		Role role = roleRepository.findByRoleName(userRequest.getRoleName());
		User user = User.builder()
				.userName(userRequest.getUserName())
				.passWord(encoder.encode(userRequest.getPassWord()))
				.email(userRequest.getEmail())
				.fullName(userRequest.getFullName())
				.roles(Collections.singleton(role))
				.build();
		return userRepository.save(user).getId();
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

}
