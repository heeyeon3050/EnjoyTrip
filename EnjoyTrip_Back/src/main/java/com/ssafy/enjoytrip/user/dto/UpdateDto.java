package com.ssafy.enjoytrip.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDto {
	private String name;
	private String password_old;
	private String password_new;
	private String email;
	private String imageUrl;
}
