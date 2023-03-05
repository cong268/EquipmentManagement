package com.management.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "tb_device_draft")
@Data
public class DeviceDraft {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long deviceId;
	private String controlNo;
	private String equipmentName;
	private String maker;
	private String statusWork;
	private String storePlace;
	private Integer calibrationFreq;
	private Date lastCalibDate;
	private Date calibPlan;
	private String calibOrg;
	private BigDecimal calibPrice;
	private String calibStatus;
	private String msa;
	private String remark;
}
