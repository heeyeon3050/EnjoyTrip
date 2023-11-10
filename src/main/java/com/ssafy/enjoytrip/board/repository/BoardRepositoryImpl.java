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
