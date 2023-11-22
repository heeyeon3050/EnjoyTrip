package com.ssafy.enjoytrip.attraction.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;

public interface AttractionRepositoryCustom {
	Page<Attraction> findDynamicQueryAdvance(List<AttractionCategory> categories, String keyword, double x, double y, Pageable pageable);

}
