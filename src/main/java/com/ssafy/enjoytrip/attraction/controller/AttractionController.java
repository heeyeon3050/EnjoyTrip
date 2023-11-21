package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
import com.ssafy.enjoytrip.common.rq.Rq;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController {
	private final Rq rq;
	private final AttractionService attractionService;

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody AttractionDto attractionDto) {
		return ResponseEntity.ok(attractionService.create(attractionDto));
	}

	@PatchMapping("/{attractionId}/delete")
	public ResponseEntity<?> delete(@PathVariable Long attractionId) {
		return ResponseEntity.ok(attractionService.delete(attractionId));
	}

	@PatchMapping("/{attractionId}/update")
	public ResponseEntity<?> update(@PathVariable Long attractionId, @RequestBody AttractionDto attractionDto) {
		return ResponseEntity.ok(attractionService.update(attractionId, attractionDto));
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(required = false) List<AttractionCategory> categories,
		@RequestParam(required = false) String keyword) {
		return ResponseEntity.ok(attractionService.search(categories, keyword));
	}

	@PostMapping("/{attractionId}/like")
	public ResponseEntity<?> like(@PathVariable Long attractionId) {
		return ResponseEntity.ok(attractionService.like(attractionId, rq.getUser()));
	}
}

