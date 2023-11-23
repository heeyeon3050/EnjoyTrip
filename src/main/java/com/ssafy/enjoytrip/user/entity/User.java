package com.ssafy.enjoytrip.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.auth.entity.Authority;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.common.BaseEntity;
import com.ssafy.enjoytrip.user.dto.UpdateDto;
import com.ssafy.enjoytrip.user.dto.UserDto;

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
	@Column(unique = true)
	private String userId;
	private String password;
	private String email;
	private String name;
	private String image_url;
	@Enumerated(EnumType.STRING)
	private Authority authority;
	@ManyToMany(mappedBy = "board_users")
	@JsonIgnoreProperties("board_users")
	@JsonIgnore
	private Set<Board> boards = new HashSet<>();
	@ManyToMany(mappedBy = "attraction_users")
	@JsonIgnoreProperties("attraction_users")
	private Set<Attraction> attractions = new HashSet<>();

	public static User toUser(UserDto userDto, Authority authority, PasswordEncoder passwordEncoder) {
		return User.builder()
			.userId(userDto.getUserId())
			.email(userDto.getEmail())
			.password(passwordEncoder.encode(userDto.getPassword()))
			.name(userDto.getName())
			.image_url(userDto.getImageUrl())
			.authority(authority)
			.build();
	}

	public void update(UpdateDto updateDto, PasswordEncoder passwordEncoder) {
		if (updateDto.getEmail() != null) {
			this.email = updateDto.getEmail();
		}
		if (updateDto.getPassword_new() != null) {
			this.password = passwordEncoder.encode(updateDto.getPassword_new());
		}
		if (updateDto.getName() != null) {
			this.name = updateDto.getName();
		}
		if (updateDto.getImageUrl() != null) {
			this.image_url = updateDto.getImageUrl();
		}
	}
}
