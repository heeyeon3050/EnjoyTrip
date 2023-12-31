package com.ssafy.enjoytrip.comment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.attraction.exception.AttractionNotFoundException;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.repository.BoardRepository;
import com.ssafy.enjoytrip.comment.dto.CommentDto;
import com.ssafy.enjoytrip.comment.dto.CommentResponseDto;
import com.ssafy.enjoytrip.comment.entity.Comment;
import com.ssafy.enjoytrip.comment.repository.CommentRepository;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public CommonResponse create(CommentDto commentDto, User writer) {
		Board board = boardRepository.findById(commentDto.getBoardId())
			.orElseThrow(() -> new IllegalArgumentException("게시판 번호가 적정하지 않음"));
		Comment comment = commentRepository.save(
			Comment.toComment(commentDto.getContent(), writer, board));
		CommentResponseDto commentResponseDto = CommentResponseDto.toCommentResponseDto(comment);
		return new CommonResponse(true, "Success to create comment", commentResponseDto);
	}

	public CommonResponse getByBoardId(Long boardId, Pageable pageable) {
		Page<Comment> comments = commentRepository.findByBoardIdOrderByCreatedAtDesc(boardId, pageable);
		List<CommentResponseDto> commentResponseDtos = comments.stream()
			.map(CommentResponseDto::toCommentResponseDto)
			.collect(Collectors.toList());
		return new CommonResponse(true, "Success to get comment.",
			new PageImpl<>(commentResponseDtos, comments.getPageable(), comments.getTotalElements()));
	}

	@Transactional
	public CommonResponse update(Long id, CommentDto commentDto) {
		Optional<Comment> optionalComment = commentRepository.findById(id);

		if (optionalComment.isPresent()) {
			Comment existComment = optionalComment.get();
			existComment.update(commentDto);
			Comment comment = commentRepository.save(existComment);
			CommentResponseDto commentResponseDto = CommentResponseDto.toCommentResponseDto(comment);
			return new CommonResponse(true, "Success to update comment.", commentResponseDto);
		}
		throw new AttractionNotFoundException(String.format("댓글(%s)를 찾을 수 없습니다.", id));
	}

	@Transactional
	public CommonResponse delete(Long id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);

		if (optionalComment.isPresent()) {
			Comment existComment = optionalComment.get();
			existComment.delete();
			return new CommonResponse(true, "Success to delete comment.", commentRepository.save(existComment));
		}
		throw new AttractionNotFoundException(String.format("댓글(%s)를 찾을 수 없습니다.", id));
	}
}
