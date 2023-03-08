package com.management.entity;

import java.time.Instant;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_refresh_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(nullable = false, unique = true)
	private String refreshToken;

	@Column(nullable = false)
	private Instant expiryDate;
}
