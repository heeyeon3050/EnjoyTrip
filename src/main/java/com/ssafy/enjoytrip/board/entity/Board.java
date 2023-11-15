package com.ssafy.enjoytrip.board.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Where;

import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.common.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
public class Board extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	@Enumerated(EnumType.STRING)
	private BoardCategory category;
	private Double latitude;
	private Double longitude;

	public static Board toBoard(BoardDto boardDto) {
		return Board.builder()
			.title(boardDto.getTitle())
			.content(boardDto.getContent())
			.category(boardDto.getCategory())
			.latitude(boardDto.getLatitude())
			.longitude(boardDto.getLongitude())
			.build();
	}

	public void update(BoardDto boardDto) {
		if (boardDto.getTitle() != null) { this.title = boardDto.getTitle(); }
		if (boardDto.getContent() != null) { this.content = boardDto.getContent(); }
		if (boardDto.getCategory() != null) { this.category = boardDto.getCategory(); }
		if (boardDto.getLatitude() != null) { this.latitude = boardDto.getLatitude(); }
		if (boardDto.getLongitude() != null) { this.longitude = boardDto.getLongitude(); }
	}
}
