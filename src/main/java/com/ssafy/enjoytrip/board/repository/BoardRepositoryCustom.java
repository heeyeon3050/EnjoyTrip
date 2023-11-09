package com.ssafy.enjoytrip.board.repository;

import java.util.List;

import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;

public interface BoardRepositoryCustom {
	List<Board> findDynamicQueryAdvance(BoardCategory category, String keyword);

}
