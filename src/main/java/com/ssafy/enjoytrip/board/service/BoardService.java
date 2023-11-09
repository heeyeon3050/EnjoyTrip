package com.ssafy.enjoytrip.board.service;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
// import com.ssafy.enjoytrip.board.entity.QBoard;
import com.ssafy.enjoytrip.board.exception.BoardNotFoundException;
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

	@Transactional
	public CommonResponse update(Long id, BoardDto boardDto) {
		Optional<Board> optionalBoard = boardRepository.findById(id);

		if(optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			board.update(boardDto);
			return new CommonResponse(true, "Success to update board.", boardRepository.save(board));
		}
		throw new BoardNotFoundException(String.format("게시판(%s)을 찾을 수 없습니다.", id));
	}

	@Transactional
	public CommonResponse delete(Long id) {
		Optional<Board> optionalBoard = boardRepository.findById(id);

		if(optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			board.delete();
			CommonResponse commonResponse = new CommonResponse(true, "Success to delete Attraction.",
				boardRepository.save(board));
			return commonResponse;

		}
		throw new BoardNotFoundException(String.format("게시판(%s)을 찾을 수 없습니다.", id));
	}
	//
	// public CommonResponse search(BoardCategory category, String keyword) {
	// 	BooleanBuilder builder = new BooleanBuilder();
	// 	QBoard qBoard = QBoard.board;
	//
	// 	if (category != null) {
	// 		builder.and(qBoard.category.eq(category));
	// 	}
	//
	// 	if (keyword != null) {
	// 		builder.and(qBoard.title.likeIgnoreCase("%" + keyword + "%"));
	// 	}
	//
	// 	Predicate predicate = builder.getValue();
	// 	Specification<Board> specification = (root, query, criteriaBuilder) -> (javax.persistence.criteria.Predicate)predicate;
	// 	Iterable<Board> boards = boardRepository.findAll(specification);
	//
	// 	return new CommonResponse(true, "Success to get Attraction.", boards);
	// }
}