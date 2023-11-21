package com.ssafy.enjoytrip.comment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Override
	Optional<Comment> findById(Long aLong);

	@Override
	List<Comment> findAll();

	List<Comment> findAllByWriterId(Long writerId);

	Page<Comment> findByBoardId(Long boardId, Pageable pageable);

	long countByBoardId(Long boardId);
}
