package com.ssafy.enjoytrip.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
import com.ssafy.enjoytrip.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody BoardDto boardDto) {
		return ResponseEntity.ok(boardService.create(boardDto));
	}

	@PatchMapping("/{boardId}/update")
	public ResponseEntity<?> update(@PathVariable Long boardId, @RequestBody BoardDto boardDto) {
		return ResponseEntity.ok(boardService.update(boardId, boardDto));
	}

	@PatchMapping("/{boardId}/delete")
	public ResponseEntity<?> delete(@PathVariable Long boardId) {
		return ResponseEntity.ok(boardService.delete(boardId));
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(required = false) BoardCategory category, @RequestParam(required = false) String keyword) {
		return ResponseEntity.ok(boardService.search(category, keyword));
	}
}