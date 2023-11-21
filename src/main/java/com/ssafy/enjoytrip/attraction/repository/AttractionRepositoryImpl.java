package com.ssafy.enjoytrip.attraction.repository;

import static com.ssafy.enjoytrip.attraction.entity.QAttraction.*;
import static com.ssafy.enjoytrip.board.entity.QBoard.*;

import java.util.List;

import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttractionRepositoryImpl implements AttractionRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Attraction> findDynamicQueryAdvance(AttractionCategory category, String keyword) {
		return queryFactory
			.selectFrom(attraction)
			.where(eqCategory(category),
				eqKeyword(keyword))
			.fetch();
	}

	private BooleanExpression eqCategory(AttractionCategory category) {
		if (category == null) {
			return null;
		}
		return attraction.category.eq(category);
	}

	private BooleanExpression eqKeyword(String keyword) {
		if (!StringUtils.hasText(keyword)) { // hasText는 null이 아니고, 길이가 0보다 크며, 공백만 있는 것이 아닌 문자열을 검사
			return null;
		}
		return attraction.title.containsIgnoreCase(keyword);
	}
}
