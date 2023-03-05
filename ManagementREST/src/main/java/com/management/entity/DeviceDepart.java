package com.management.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_device_depart")
@Data
public class DeviceDepart implements Serializable{
	@Id
	private Long deviceId;
	@Id
	private Long departId;
}
