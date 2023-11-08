package com.ssafy.enjoytrip.attraction.controller;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.entity.Category;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PatchMapping("/{attractionId}")
    public ResponseEntity<?> update(@PathVariable Long attractionId, @RequestBody AttractionDto attractionDto) {
        return ResponseEntity.ok(attractionService.update(attractionId, attractionDto));
    }

//    @GetMapping("/{category}")
//    public ResponseEntity<?> getByCategory(@Validated @PathVariable("category") Category category) {
//        return ResponseEntity.ok(attractionService.getByCategory(category));
//    }
}
