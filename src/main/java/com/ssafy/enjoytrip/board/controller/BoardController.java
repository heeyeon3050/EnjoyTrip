package com.ssafy.enjoytrip.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.dto.BoardDto;
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
}