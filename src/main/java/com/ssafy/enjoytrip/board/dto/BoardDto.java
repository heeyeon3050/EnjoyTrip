package com.ssafy.enjoytrip.board.dto;

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
	private BoardCategory category;
	private double latitude;
	private double longitude;
}