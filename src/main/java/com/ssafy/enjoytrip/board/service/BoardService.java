package com.ssafy.enjoytrip.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.repository.BoardRepository;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	@Transactional
	public CommonResponse create(BoardDto boardDto) {

		Board board = Board.toBoard(boardDto);

		return new CommonResponse(true, "Success to create board", boardRepository.save(board));
	}
}
