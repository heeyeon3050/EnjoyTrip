package com.ssafy.enjoytrip.attraction.dto;

import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDto {
    @Enumerated(EnumType.STRING)
    private AttractionCategory attractionCategory;
}
