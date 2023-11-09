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
public class AttractionDto {
    private String title;
    private Double latitude;
    private Double longitude;
    private String imageUrl;
    private String address1;
    private String address2;
    @Enumerated(EnumType.STRING)
    private AttractionCategory category;
    private String description;;
}
