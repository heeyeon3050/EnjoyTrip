package com.ssafy.enjoytrip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/regist")
	public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.regist(userDto));
	}
	@GetMapping("/{userId}/idCheck")
	public ResponseEntity<?> idCheck(@PathVariable String userId) {
		return ResponseEntity.ok(userService.idCheck(userId));
	}

	@PatchMapping("/{userId}/delete")
	public ResponseEntity<?> delete(@PathVariable String userId) {
		return ResponseEntity.ok(userService.delete(userId));
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<?> update(@PathVariable String userId, @RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.update(userId, userDto));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getById(@PathVariable String userId) {
		return ResponseEntity.ok(userService.getById(userId));
	}

	@GetMapping("/list")
	public ResponseEntity<?> getList() {
		return ResponseEntity.ok(userService.getList());
	}
}
