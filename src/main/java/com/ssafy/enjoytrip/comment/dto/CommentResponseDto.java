package com.ssafy.enjoytrip.comment.dto;

import com.ssafy.enjoytrip.board.dto.BoardResponseDto;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.comment.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {
	private Long id;
	private String writerId;
	private Long boardId;
	private String content;

	public static CommentResponseDto toCommentResponseDto(Comment comment) {
		if (comment == null) {
			return null;
		}

		return CommentResponseDto.builder()
			.id(comment.getId())
			.writerId(comment.getWriter().getUserId())
			.boardId(comment.getBoard().getId())
			.content(comment.getContent())
			.build();
	}
}
