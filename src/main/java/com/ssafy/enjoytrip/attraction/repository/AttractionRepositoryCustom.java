package com.ssafy.enjoytrip.attraction.repository;

import java.util.List;

import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;

public interface AttractionRepositoryCustom {
	List<Attraction> findDynamicQueryAdvance(List<AttractionCategory> categories, String keyword);

}
