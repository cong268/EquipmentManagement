package com.management.service;

import com.management.payload.request.UserRequest;

public interface UserService {
	public Long createUser (UserRequest userRequest);
	public void deleteUser (Long userId);
}
