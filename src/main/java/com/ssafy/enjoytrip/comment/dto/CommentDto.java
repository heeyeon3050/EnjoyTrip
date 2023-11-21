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
public class CommentDto {
	private Long writerId;
	private Long boardId;
	private String content;

	public static CommentDto fromEntity(Comment comment) {
		return new CommentDto(
			comment.getWriter().getId(),
			comment.getBoard().getId(),
			comment.getContent()
		);
	}
}
