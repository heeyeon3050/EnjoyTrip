package com.ssafy.enjoytrip.user.dto;

import java.util.Set;

import com.ssafy.enjoytrip.auth.entity.Authority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//이름, 아이디, 비밀번호, 이메일
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private String name;
	private String userId;
	private String password;
	private String email;
	@Enumerated(EnumType.STRING)
	private Authority authority;
	private String imageUrl;

	private Set<Long> boardIds;
	private Set<Long> attractionIds;
}
