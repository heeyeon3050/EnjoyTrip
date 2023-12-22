package com.ssafy.enjoytrip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoytrip.common.rq.Rq;
import com.ssafy.enjoytrip.user.dto.UpdateDto;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final Rq rq;
	private final UserService userService;

	@PostMapping("/join")
	public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.join(userDto));
	}

	@PatchMapping("/{userId}/delete")
	public ResponseEntity<?> delete(@PathVariable String userId) {
		return ResponseEntity.ok(userService.delete(userId));
	}

	@PatchMapping("/{userId}/update")
	public ResponseEntity<?> update(@PathVariable String userId, @RequestBody UpdateDto updateDto) {
		return ResponseEntity.ok(userService.update(userId, updateDto, rq.getUser()));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getById(@PathVariable String userId) {
		return ResponseEntity.ok(userService.getById(userId));
	}

	@GetMapping("/list")
	public ResponseEntity<?> getList() {
		return ResponseEntity.ok(userService.getList());
	}

	@GetMapping("/{userId}/IdCheck")
	public ResponseEntity<?> checkIdDuplication(@PathVariable String userId) {
		return ResponseEntity.ok(userService.checkIdDuplication(userId));
	}

	@GetMapping("/{name}/NameCheck")
	public ResponseEntity<?> checkNameDuplication(@PathVariable String name) {
		return ResponseEntity.ok(userService.checkNameDuplication(name));
	}

	@GetMapping("/{email}/EmailCheck")
	public ResponseEntity<?> checkEmailDuplication(@PathVariable String email) {
		return ResponseEntity.ok(userService.checkEmailDuplication(email));
	}
}
