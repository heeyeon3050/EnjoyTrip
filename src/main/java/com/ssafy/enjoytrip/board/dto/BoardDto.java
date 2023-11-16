package com.ssafy.enjoytrip.board.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class BoardDto {
	private Long writerId;
	private String title;
	private String content;
	@Enumerated(EnumType.STRING)
	private BoardCategory category;
	private Double latitude;
	private Double longitude;

	private Set<Long> userIds;
}