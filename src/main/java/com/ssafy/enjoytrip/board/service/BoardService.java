package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.dto.ResponseDto;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
import com.ssafy.enjoytrip.board.exception.BoardNotFoundException;
import com.ssafy.enjoytrip.board.repository.BoardRepository;
import com.ssafy.enjoytrip.board.repository.BoardRepositoryCustom;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final BoardRepositoryCustom boardRepositoryCustom;

	@Transactional
	public CommonResponse create(BoardDto boardDto, User user) {

		Board board = Board.toBoard(boardDto, user);

		return new CommonResponse(true, "Success to create board", boardRepository.save(board));
	}

	@Transactional
	public CommonResponse update(Long id, BoardDto boardDto) {
		Optional<Board> optionalBoard = boardRepository.findById(id);

		if (optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			board.update(boardDto);
			return new CommonResponse(true, "Success to update board.", boardRepository.save(board));
		}
		throw new BoardNotFoundException(String.format("게시판(%s)을 찾을 수 없습니다.", id));
	}

	@Transactional
	public CommonResponse delete(Long id) {
		Optional<Board> optionalBoard = boardRepository.findById(id);

		if (optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			board.delete();
			CommonResponse commonResponse = new CommonResponse(true, "Success to delete board.",
				boardRepository.save(board));
			return commonResponse;

		}
		throw new BoardNotFoundException(String.format("게시판(%s)을 찾을 수 없습니다.", id));
	}

	public CommonResponse search(BoardCategory category, String keyword) {
		List<Board> boards = boardRepositoryCustom.findDynamicQueryAdvance(category, keyword);
		List<ResponseDto> responseDtos = boards.stream()
			.map(ResponseDto::toResponseDto)
			.collect(Collectors.toList());
		System.out.println("aaaaaaaa");
		return new CommonResponse(true, "Success to get board.", responseDtos);
	}

	public CommonResponse like(Long id, User user) {
		Optional<Board> optionalBoard = boardRepository.findById(id);

		if (optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			board.getBoard_users().add(user);
			return new CommonResponse(true, "Success to like board", boardRepository.save(board));
		}

		throw new BoardNotFoundException(String.format("게시판(%s)을 찾을 수 없습니다.", id));
	}

	public CommonResponse getById(Long boardId) {
		return new CommonResponse(true, "Success to get User.", boardRepository.findById(boardId));
	}
}