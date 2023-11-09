package com.ssafy.enjoytrip.attraction.controller;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController {
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
	public ResponseEntity<?> getByCategory(@RequestParam AttractionCategory category) {
		return ResponseEntity.ok(attractionService.getByCategory(category));
	}
}

