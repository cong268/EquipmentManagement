package com.management.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
	private String userName;
	private String passWord;
	private String fullName;
	private String email;
}
