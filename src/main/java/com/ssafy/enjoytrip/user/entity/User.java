package com.ssafy.enjoytrip.user.entity;

import javax.persistence.*;

import com.ssafy.enjoytrip.auth.entity.Authority;
import com.ssafy.enjoytrip.common.BaseEntity;
import com.ssafy.enjoytrip.user.dto.RegistDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;
	private String password;
	private String email;
	private String name;
	private String image_url;
	@Enumerated(EnumType.STRING)
	private Authority authority;

	public static User toUser(RegistDto registDto, Authority authority, PasswordEncoder passwordEncoder) {
		return User.builder()
			.userId(registDto.getUserId())
			.email(registDto.getEmail())
			.password(passwordEncoder.encode(registDto.getPassword()))
			.name(registDto.getName())
			.authority(authority)
			.build();
	}
}
