package com.ssafy.enjoytrip.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;

public interface BoardRepositoryCustom {
	Page<Board> findDynamicQueryAdvance(BoardCategory category, String keyword, Pageable pageable);

}
