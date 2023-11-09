package com.ssafy.enjoytrip.board.repository;

import static com.ssafy.enjoytrip.board.entity.QBoard.*;

import java.util.List;

import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Board> findDynamicQueryAdvance(BoardCategory category, String keyword) {
		return queryFactory
			.selectFrom(board)
			.where(eqCategory(category),
				eqKeyword(keyword))
			.fetch();
	}

	private BooleanExpression eqCategory(BoardCategory category) {
		if (StringUtils.isEmpty(category)) {
			return null;
		}
		return board.category.eq(category);
	}

	private BooleanExpression eqKeyword(String keyword) {
		if (StringUtils.isEmpty(keyword)) {
			return null;
		}
		return board.title.containsIgnoreCase(keyword);
	}
}
