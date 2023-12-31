package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import com.ssafy.enjoytrip.common.rq.Rq;

import lombok.RequiredArgsConstructor;

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

	@GetMapping("/list")
	public ResponseEntity<?> getItems(@RequestParam(required = false) List<AttractionCategory> categories,
		@RequestParam(required = false) String keyword, @RequestParam(required = false, defaultValue = "37.5012739267") double latitude, @RequestParam(required = false, defaultValue = "127.0396131393") double longitude, @PageableDefault(size = 10) Pageable pageable) {
		return ResponseEntity.ok(attractionService.getItems(categories, keyword, latitude, longitude, rq.getUser(), pageable));
	}

	@PostMapping("/{attractionId}/like")
	public ResponseEntity<?> like(@PathVariable Long attractionId) {
		return ResponseEntity.ok(attractionService.like(attractionId, rq.getUser()));
	}
}

