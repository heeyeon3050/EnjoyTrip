package com.ssafy.enjoytrip.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.enjoytrip.board.entity.Board;

public interface BoardRepositoryCustom {
	Page<Board> findFaxesByFaxTitle(Long userId, String searchWord, Pageable pageable);

}
