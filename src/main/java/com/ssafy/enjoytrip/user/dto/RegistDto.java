package com.ssafy.enjoytrip.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//이름, 아이디, 비밀번호, 이메일
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistDto {
	private String name;
	private String userId;
	private String password;
	private String email;
}
