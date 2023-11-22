package com.ssafy.enjoytrip.board.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
import com.ssafy.enjoytrip.board.service.BoardService;
import com.ssafy.enjoytrip.common.rq.Rq;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final Rq rq;
	private final BoardService boardService;

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody BoardDto boardDto) {
		return ResponseEntity.ok(boardService.create(boardDto, rq.getUser()));
	}

	@PatchMapping("/{boardId}/update")
	public ResponseEntity<?> update(@PathVariable Long boardId, @RequestBody BoardDto boardDto) {
		return ResponseEntity.ok(boardService.update(boardId, boardDto));
	}

	@PatchMapping("/{boardId}/delete")
	public ResponseEntity<?> delete(@PathVariable Long boardId) {
		return ResponseEntity.ok(boardService.delete(boardId));
	}

	@GetMapping("/list")
	public ResponseEntity<?> getItems(@RequestParam(required = false) BoardCategory category,
		@RequestParam(required = false) String keyword, @PageableDefault(size = 5) Pageable pageable) {
		return ResponseEntity.ok(boardService.getItems(category, keyword, pageable));
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<?> getById(@PathVariable Long boardId) {
		return ResponseEntity.ok(boardService.getById(boardId));
	}

	@PostMapping("/{boardId}/like")
	public ResponseEntity<?> like(@PathVariable Long boardId) {
		return ResponseEntity.ok(boardService.like(boardId, rq.getUser()));
	}


}