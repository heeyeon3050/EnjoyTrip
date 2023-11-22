package com.ssafy.enjoytrip.comment.dto;

import java.time.LocalDateTime;

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
	private String writerName;
	private LocalDateTime createdAt;
	private String content;

	public static CommentResponseDto toCommentResponseDto(Comment comment) {
		if (comment == null) {
			return null;
		}

		return CommentResponseDto.builder()
			.id(comment.getId())
			.writerName(comment.getWriter().getName())
			.createdAt(comment.getCreatedAt())
			.boardId(comment.getBoard().getId())
			.content(comment.getContent())
			.build();
	}
}
