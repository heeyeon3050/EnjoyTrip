package com.ssafy.enjoytrip.comment.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.comment.dto.CommentDto;
import com.ssafy.enjoytrip.comment.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody CommentDto commentDto) {
		return ResponseEntity.ok(commentService.create(commentDto));
	}

	@PatchMapping("/{commentId}/delete")
	public ResponseEntity<?> delete(@PathVariable Long commentId) {
		return ResponseEntity.ok(commentService.delete(commentId));
	}

	@PatchMapping("/{commentId}/update")
	public ResponseEntity<?> update(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
		return ResponseEntity.ok(commentService.update(commentId, commentDto));
	}

	@GetMapping("/board/{boardId}")
	public ResponseEntity<?> getByBoardId(@PathVariable Long boardId, @PageableDefault(size = 5) Pageable pageable) {
		return ResponseEntity.ok(commentService.getByBoardId(boardId, pageable));
	}

	@GetMapping("/writer/{writerId}")
	public ResponseEntity<?> getByCategory(@PathVariable Long writerId) {
		return ResponseEntity.ok(commentService.getByWriterId(writerId));
	}
}

