package com.ssafy.enjoytrip.board.dto;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
	private Long writerId;
	// private String writerName;
	private String title;
	private String content;
	@Enumerated(EnumType.STRING)
	private BoardCategory category;
	private Double latitude;
	private Double longitude;

	private Set<Long> userIds;

	private Long commentCount;

	public static ResponseDto toResponseDto(Board board) {
		if (board == null) {
			return null;
		}

		// System.out.println("User : " + board.getUser());

		return ResponseDto.builder()
			.title(board.getTitle())
			.writerId(board.getWriterId())
			.content(board.getContent())
			.category(board.getCategory())
			.latitude(board.getLatitude())
			.longitude(board.getLongitude())
			.build();
	}
}
