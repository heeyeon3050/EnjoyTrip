package com.ssafy.enjoytrip.attraction.repository;

import static com.ssafy.enjoytrip.attraction.entity.QAttraction.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttractionRepositoryImpl implements AttractionRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Attraction> findDynamicQueryAdvance(List<AttractionCategory> categories, String keyword,
		Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();

		if (categories != null && !categories.isEmpty()) {
			builder.andAnyOf(categories.stream()
				.map(category -> attraction.category.eq(category))
				.toArray(BooleanExpression[]::new));
		}

		if (StringUtils.hasText(keyword)) {
			builder.and(attraction.title.containsIgnoreCase(keyword));
		}

		JPAQuery<Attraction> query = queryFactory
			.selectFrom(attraction)
			.where(builder);

		long total = query.fetchCount();

		List<Attraction> attractions = query
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		return new PageImpl<>(attractions, pageable, total);
	}
}
