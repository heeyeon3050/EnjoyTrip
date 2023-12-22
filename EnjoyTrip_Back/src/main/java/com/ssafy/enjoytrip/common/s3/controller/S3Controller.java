package com.ssafy.enjoytrip.common.s3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {
	// @ResponseBody   // Long 타입을 리턴하고 싶은 경우 붙여야 함 (Long - 객체)
	// @PostMapping(value="/board/new",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	// public ResponseEntity<?> saveDiary(HttpServletRequest request, @RequestParam(value="image") MultipartFile image, Board board) throws
	// 	IOException {
	// 	System.out.println("BoardController.saveBoard");
	// 	System.out.println(image);
	// 	System.out.println(board);
	// 	System.out.println("------------------------------------------------------");
	// 	return ResponseEntity.ok(boardService.keepBoard(image, board));
	// }
}
