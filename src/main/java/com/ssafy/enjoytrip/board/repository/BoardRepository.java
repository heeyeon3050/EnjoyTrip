package com.ssafy.enjoytrip.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Page<Board> findAllOrderByLikes(Pageable pageable);
}
