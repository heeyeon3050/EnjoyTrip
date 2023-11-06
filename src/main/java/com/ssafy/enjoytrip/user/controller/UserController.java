package com.ssafy.enjoytrip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.dto.RegistDto;
import com.ssafy.enjoytrip.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/regist")
	public ResponseEntity<?> signup(@RequestBody RegistDto registDto) {
		return ResponseEntity.ok(userService.regist(registDto));
	}
}
