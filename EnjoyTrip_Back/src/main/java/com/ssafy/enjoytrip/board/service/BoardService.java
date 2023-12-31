package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.dto.BoardResponseDto;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
import com.ssafy.enjoytrip.board.exception.BoardNotFoundException;
import com.ssafy.enjoytrip.board.repository.BoardRepository;
import com.ssafy.enjoytrip.board.repository.BoardRepositoryCustom;
import com.ssafy.enjoytrip.comment.repository.CommentRepository;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import com.ssafy.enjoytrip.common.s3.service.S3Uploader;
import com.ssafy.enjoytrip.image.service.ImageService;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final S3Uploader s3Uploader;
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;
	private final BoardRepositoryCustom boardRepositoryCustom;
	private final ImageService imageService;

	// @Transactional
	// public CommonResponse create(BoardDto boardDto, User user) throws IOException {
	// 	List<Image> images = boardDto.getImages().stream().map(i -> {
	// 			try {
	// 				return imageService.upload(i);
	// 			} catch (IOException e) {
	// 				throw new RuntimeException(e);
	// 			}
	// 		}).collect(Collectors.toList());
	// 	Board board = Board.toBoard(boardDto, user, images);
	//
	//
	// 	// System.out.println("image..............." + image);
	// 	// System.out.println("board...................." + board);
	// 	// imageService.upload(image);
	//
	// 	return new CommonResponse(true, "Success to create board", boardRepository.save(board));
	// }

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

	public CommonResponse getItems(BoardCategory category, String keyword, User user, Pageable pageable) {
		Page<Board> boards = boardRepositoryCustom.findDynamicQueryAdvance(category, keyword, pageable);
		List<BoardResponseDto> boardResponseDtos = boards.stream()
			.map(board -> {
				long commentCount = commentRepository.countByBoardId(board.getId());
				return BoardResponseDto.toBoardResponseDto(board, commentCount, board.getBoard_users().contains(user));
			})
			.collect(Collectors.toList());

		return new CommonResponse(true, "Success to get board.",
			new PageImpl<>(boardResponseDtos, boards.getPageable(), boards.getTotalElements()));
	}

	public CommonResponse like(Long id, User user) {
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (optionalBoard.isPresent()) {
			Board board = optionalBoard.get();

			if (board.getBoard_users().contains(user))
				board.getBoard_users().remove(user);
			else
				board.getBoard_users().add(user);

			Board saved = boardRepository.save(board);

			return new CommonResponse(true, "Success to like board",
				BoardResponseDto.toBoardResponseDto(saved, Long.valueOf(saved.getComments().size()),
					saved.getBoard_users().contains(user)));
		}

		throw new BoardNotFoundException(String.format("게시판(%s)을 찾을 수 없습니다.", id));
	}

	public CommonResponse getById(Long boardId, User user) {
		Optional<Board> optionalBoard = boardRepository.findById(boardId);

		if (optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			long commentCount = commentRepository.countByBoardId(board.getId());
			BoardResponseDto boardResponseDto = BoardResponseDto.toBoardResponseDto(board, commentCount,
				board.getBoard_users().contains(user));

			return new CommonResponse(true, "Success to get board", boardResponseDto);
		}

		throw new BoardNotFoundException(String.format("게시판(%s)을 찾을 수 없습니다.", boardId));
	}

	public CommonResponse getBestItems(Pageable pageable) {
		Page<Board> boards = boardRepository.findAllOrderByBoardUsersSizeDesc(pageable);

		List<BoardResponseDto> boardResponseDtos = boards.getContent()
			.stream()
			.map(board -> BoardResponseDto.toBoardResponseDto(board))
			.collect(Collectors.toList());

		return new CommonResponse(true, "Success to get board.",
			new PageImpl<>(boardResponseDtos, boards.getPageable(), boards.getTotalElements()));
	}

	// public CommonResponse keepBoard(MultipartFile image, Board board) throws IOException {
	// 	System.out.println("Diary service saveDiary");
	// 	if(!image.isEmpty()) {
	// 		String storedFileName = s3Uploader.upload(image,"images");
	// 		Image newImage = Image.builder()
	// 			.url(storedFileName)
	// 			.board(board)
	// 			.build();
	// 		//board.getImages().add(newImage);
	// 	}
	// 	Board savedBoard = boardRepository.save(board);
	// 	return new CommonResponse(true, "Success to get board", savedBoard);
	// }
}