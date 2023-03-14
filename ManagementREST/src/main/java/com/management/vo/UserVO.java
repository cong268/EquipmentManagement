package com.management.vo;

import java.util.Set;

import com.management.entity.Role;
import com.management.entity.User;
import com.management.util.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	private Long id;
	private String userName;
	private String passWord;
	private String fullName;
	private String email;
	private Long parentId;
	private String logo;
	private DepartmentVO department;
	private Set<Role> roles;
	private EStatus status;
	
	public UserVO convertToVO (User user) {
		id = user.getId();
		userName = user.getUserName();
		fullName = user.getFullName();
		email = user.getEmail();
		parentId = user.getParentId();
		roles = user.getRoles();
		status = user.getStatus();
		return this;
	}
}
