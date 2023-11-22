package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.dto.BoardResponseDto;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
import com.ssafy.enjoytrip.board.exception.BoardNotFoundException;
import com.ssafy.enjoytrip.board.repository.BoardRepository;
import com.ssafy.enjoytrip.board.repository.BoardRepositoryCustom;
import com.ssafy.enjoytrip.comment.repository.CommentRepository;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;
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

	public CommonResponse getItems(BoardCategory category, String keyword, Pageable pageable) {
		Page<Board> boards = boardRepositoryCustom.findDynamicQueryAdvance(category, keyword, pageable);
		List<BoardResponseDto> boardResponseDtos = boards.stream()
			.map(board -> {
				long commentCount = commentRepository.countByBoardId(board.getId());
				return BoardResponseDto.toBoardResponseDto(board, commentCount);
			})
			.collect(Collectors.toList());

		return new CommonResponse(true, "Success to get board.", new PageImpl<>(boardResponseDtos, boards.getPageable(), boards.getTotalElements()));
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
		return new CommonResponse(true, "Success to get Board.", boardRepository.findById(boardId));
	}

	public CommonResponse findAll(Pageable pageable) {
		return new CommonResponse(true, "Success to get Fax.", boardRepository.findAll(pageable));
	}
}