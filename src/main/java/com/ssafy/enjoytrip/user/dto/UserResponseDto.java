package com.ssafy.enjoytrip.user.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.auth.entity.Authority;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
	private Long id;
	private String userId;
	private String email;
	private String name;
	private String image_url;
	private Authority authority;
	private Set<Board> boards;
	private Set<Attraction> attractions;

	public static UserResponseDto toUserResponseDto(User user) {
		if (user == null) {
			return null;
		}

		return UserResponseDto.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.email(user.getEmail())
			.name(user.getName())
			.image_url(user.getImage_url())
			.authority(user.getAuthority())
			.boards(user.getBoards())
			.attractions(user.getAttractions())
			.build();
	}

}
