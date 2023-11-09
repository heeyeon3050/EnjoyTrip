package com.ssafy.enjoytrip.board.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ssafy.enjoytrip.board.entity.BoardCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
	private long writerId;
	private String title;
	private String content;
	@Enumerated(EnumType.STRING)
	private BoardCategory category;
	private double latitude;
	private double longitude;
}