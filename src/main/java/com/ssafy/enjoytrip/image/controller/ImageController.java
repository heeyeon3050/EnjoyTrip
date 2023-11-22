package com.ssafy.enjoytrip.image.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.service.BoardService;
import com.ssafy.enjoytrip.image.entity.Image;
import com.ssafy.enjoytrip.image.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class ImageController {
	private final ImageService imageService;

	@ResponseBody   // Long 타입을 리턴하고 싶은 경우 붙여야 함 (Long - 객체)
	@PostMapping(value="/image/new",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> saveDiary(HttpServletRequest request, @RequestParam(value="image") MultipartFile image) throws
		IOException {
		System.out.println("BoardController.saveBoard");
		System.out.println(image);
		System.out.println("------------------------------------------------------");
		return ResponseEntity.ok(imageService.upload(image));
	}
}
