package com.ssafy.enjoytrip.board.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.enjoytrip.attraction.entity.Category;
import com.ssafy.enjoytrip.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String content;
	private int likeCount;
	@Enumerated(EnumType.STRING)
	private Category category;
	private double latitude;
	private double longitude;

	public static Board toBoard(BoardDto boardDto) {
		return Board.builder()
			.title(boardDto.getTitle())
			.content(boardDto.getContent())
			.category(boardDto.getCategory())
			.latitude(boardDto.getLatitude())
			.longitude(boardDto.getLongitude())
			.build();
	}

}
