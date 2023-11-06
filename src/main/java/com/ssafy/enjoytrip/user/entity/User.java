package com.ssafy.enjoytrip.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.enjoytrip.common.BaseEntity;
import com.ssafy.enjoytrip.user.dto.RegistDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	private int type;
	private String image_url;

	public static User toUser(RegistDto registDto) {
		return User.builder()
			.userId(registDto.getUserId())
			.email(registDto.getEmail())
			.password(registDto.getPassword())
			.name(registDto.getName())
			.build();
	}
}
