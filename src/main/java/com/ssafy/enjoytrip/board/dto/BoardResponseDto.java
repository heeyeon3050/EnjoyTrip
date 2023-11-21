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
public class BoardResponseDto {
	private Long id;
	private String writerId;
	private String writerName;
	private String title;
	private String content;
	@Enumerated(EnumType.STRING)
	private BoardCategory category;
	private Double latitude;
	private Double longitude;


	private Long commentCount;
	private Long likeCount;

	public static BoardResponseDto toBoardResponseDto(Board board) {
		if (board == null) {
			return null;
		}

		return BoardResponseDto.builder()
			.id(board.getId())
			.title(board.getTitle())
			.writerId(board.getWriter().getUserId())
			.writerName(board.getWriter().getName())
			.content(board.getContent())
			.category(board.getCategory())
			.latitude(board.getLatitude())
			.longitude(board.getLongitude())
			.likeCount(Long.valueOf(board.getBoard_users().size()))
			.commentCount(Long.valueOf(board.getComments().size()))
			.build();
	}
}
