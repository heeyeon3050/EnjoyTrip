package com.ssafy.enjoytrip.attraction.repository;

import static com.ssafy.enjoytrip.attraction.entity.QAttraction.*;

import java.util.List;

import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttractionRepositoryImpl implements AttractionRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Attraction> findDynamicQueryAdvance(List<AttractionCategory> categories, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();

		if (categories != null && !categories.isEmpty()) {
			builder.andAnyOf(categories.stream()
				.map(category -> attraction.category.eq(category))
				.toArray(BooleanExpression[]::new));
		}

		if (StringUtils.hasText(keyword)) {
			builder.and(attraction.title.containsIgnoreCase(keyword));
		}

		return queryFactory
			.selectFrom(attraction)
			.where(builder)
			.fetch();
	}
}
