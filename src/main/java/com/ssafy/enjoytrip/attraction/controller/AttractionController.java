package com.ssafy.enjoytrip.attraction.controller;

import com.ssafy.enjoytrip.attraction.dto.RegistAttractionDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController {
    private final AttractionService attractionService;

    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody RegistAttractionDto registAttractionDto) {
        return ResponseEntity.ok(attractionService.regist(registAttractionDto));
    }
}
