package com.ssafy.enjoytrip.attraction.repository;

import static com.ssafy.enjoytrip.attraction.entity.QAttraction.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttractionRepositoryImpl implements AttractionRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Attraction> findDynamicQueryAdvance(List<AttractionCategory> categories, String keyword, double x, double y,
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

			//수정
			.orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
					Expressions.stringTemplate("POINT({0}, {1})",
						x, y
					),
					Expressions.stringTemplate("POINT({0}, {1})",
						attraction.longitude,
						attraction.latitude
					)
				).asc(),
				attraction.createdAt.desc())
			//수정 끝
			.fetch();

		return new PageImpl<>(attractions, pageable, total);
	}
}
