package com.ssafy.enjoytrip.comment.dto;

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
	private Long boardId;
	private String content;

	public static CommentResponseDto toCommentResponseDto(Comment comment) {
		if (comment == null) {
			return null;
		}

		return CommentResponseDto.builder()
			.id(comment.getId())
			.boardId(comment.getBoard().getId())
			.content(comment.getContent())
			.build();
	}
}
