package com.management.vo;

import com.management.util.EStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentVO {
	private Long id;
	private String name;
	private EStatus status;
}
