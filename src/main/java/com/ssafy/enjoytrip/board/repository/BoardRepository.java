package com.ssafy.enjoytrip.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	@Query("SELECT b FROM Board b LEFT JOIN b.board_users bu GROUP BY b.id ORDER BY COUNT(bu) DESC")
	Page<Board> findAllOrderByBoardUsersSizeDesc(Pageable pageable);
}
