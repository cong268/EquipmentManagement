package com.management.service.Impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.entity.Department;
import com.management.entity.Role;
import com.management.entity.User;
import com.management.payload.request.UserRequest;
import com.management.repository.DepartmentRepository;
import com.management.repository.RoleRepository;
import com.management.repository.UserRepository;
import com.management.service.UserService;
import com.management.util.EStatus;
import com.management.vo.DepartmentVO;
import com.management.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

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
	public UserVO getUserById(Long id) {
		UserVO userVO = new UserVO();
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userVO.convertToVO(user.get());
			if (user.get().getDepartmentId() != null) {
				DepartmentVO vo = new DepartmentVO();
				Department department = departmentRepository.findByIdAndStatus(user.get().getDepartmentId(), EStatus.ACTIVE);
				if (department != null) {
					vo.setId(department.getId());
					vo.setName(department.getName());
					vo.setStatus(department.getStatus());
				}
				userVO.setDepartment(vo);
			}
		}
		return userVO;
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
	
}
