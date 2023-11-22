package com.ssafy.enjoytrip.board.repository;

import static com.ssafy.enjoytrip.attraction.entity.QAttraction.*;
import static com.ssafy.enjoytrip.board.entity.QBoard.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Board> findDynamicQueryAdvance(BoardCategory category, String keyword, Pageable pageable) {
		JPAQuery<Board> query = queryFactory
			.selectFrom(board)
			.leftJoin(board.writer).fetchJoin()
			.where(eqCategory(category), eqKeyword(keyword));

		long total = query.fetchCount(); // 전체 항목 수 계산

		List<Board> boards = query
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(board.createdAt.desc())
			.fetch();

		return new PageImpl<>(boards, pageable, total);
	}

	private BooleanExpression eqCategory(BoardCategory category) {
		if (category == null) {
			return null;
		}
		return board.category.eq(category);
	}

	private BooleanExpression eqKeyword(String keyword) {
		if (!StringUtils.hasText(keyword)) { // hasText는 null이 아니고, 길이가 0보다 크며, 공백만 있는 것이 아닌 문자열을 검사
			return null;
		}
		return board.title.containsIgnoreCase(keyword);
	}
}
