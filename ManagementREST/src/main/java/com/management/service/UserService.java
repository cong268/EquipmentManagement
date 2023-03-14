package com.management.service;

import com.management.payload.request.UserRequest;
import com.management.vo.UserVO;

public interface UserService {
	Long createUser (UserRequest userRequest);
	UserVO getUserById (Long id);
	void deleteUser (Long userId);
}
