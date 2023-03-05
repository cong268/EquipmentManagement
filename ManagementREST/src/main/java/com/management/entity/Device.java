package com.management.entity;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_device")
@Data
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String controlNo;
	private String equipmentName;
	private String maker;
	private String statusWork;
	private String storePlace;
	private Integer calibrationFreq;
	private Instant lastCalibDate;
	private Instant calibPlan;
	private String calibOrg;
	private BigDecimal calibPrice;
	private String calibStatus;
	private String msa;
	private String remark;
	private String statusApproval;
}
